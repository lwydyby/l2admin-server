package com.example.cli.service;

import com.example.cli.constant.CommonConstant;
import com.example.cli.domain.*;
import com.example.cli.entity.*;
import com.example.cli.exception.AuthException;
import com.example.cli.repository.RoleUserRepository;
import com.example.cli.repository.RouteRepository;
import com.example.cli.repository.UserRepository;
import com.example.cli.utils.JWTUtil;
import com.example.cli.utils.RequestUserHolder;
import com.example.cli.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author liwei
 * @title: UserService
 * @projectName cli
 * @description: TODO
 * @date 2019-10-21 13:54
 */
@Service
public class UserPermissionService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private RoleUserRepository roleUserRepository;
    @Autowired
    private UserService userService;

    public ResponseBean login(LoginForm loginForm){
        String username=loginForm.getUsername();
        String password=loginForm.getPassword();
        User userBean = userRepository.findByName(username);
        if (userBean.getPassword().equals(password)) {
            Map<String,Object> map=new HashMap<>();
            map.put("uuid",userBean.getId());
            map.put("token",JWTUtil.sign(userBean.getId(), username, password));

            return new ResponseBean(200, "Login success",map);
        } else {
            throw new AuthException(CommonConstant.LOGIN_EXCEPTION, "用户被禁用或密码错误");
        }
    }

    public PermissionInfo getPermissionInfo(){
        User user=RequestUserHolder.getUser();
        List<RoleUser> list=roleUserRepository.findAllByUser_id(user.getId());
        //获取可用的role
        Set<String> roleIds=list.stream()
                .filter(i->i.getIsAdd().equals(1)).map(i->i.getRole().getId()).collect(Collectors.toSet());
        Set<Role> roles=user.getRoles();
        roles.removeIf(role -> !roleIds.contains(role.getId()));
        List<Route> route=routeRepository.findAllByLock(0);
        PermissionInfo permission=new PermissionInfo();
        permission.setUserName(user.getName());
        if(!user.getIsAdmin().equals(0)){
            permission.setUserRoles(getUserRoles(user.getRoles()));
            permission.setUserPermissions(getUserPermissions(user.getRoles()));
            permission.setAccessMenus(getUserMenu(user.getRoles()));
            permission.setAccessRoutes(getRoute(route));
            permission.setAccessInterfaces(getInterface(getAllMenu(user.getRoles())));
        }
        permission.setAvatarUrl("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"); //
        permission.setIsAdmin(user.getIsAdmin());
        return permission;
    }



    private Set<InterfaceInfo> getInterface(List<Menu> list){
        Set<InterfaceInfo> result=new HashSet<>();
        for(Menu menu:list){
            Set<Interface> interfaces=menu.getInterfaces();
            for(Interface functionInterface:interfaces){
                InterfaceInfo interfaceInfo=new InterfaceInfo();
                interfaceInfo.setMethod(functionInterface.getMethod());
                interfaceInfo.setPath(functionInterface.getPath());
                result.add(interfaceInfo);
            }
        }
        return result;
    }


    private List<RouteInfo> getRoute(List<Route> routes){
        List<RouteInfo> result=new ArrayList<>();
        for(Route route:routes){
            RouteInfo routeInfo=route2Info(route);
            routeInfo.setId(route.getId());
            routeInfo.setParentId(route.getParentId());
            result.add(routeInfo);
        }
        return TreeUtils.getMenuTreeList(result);
    }

    private RouteInfo route2Info(Route route){
        RouteInfo childRoute=new RouteInfo();
        childRoute.setName(route.getName());
        childRoute.setPath(route.getPath());
        childRoute.setSort(route.getSort());
        childRoute.setComponent(route.getComponent());
        childRoute.setComponentPath(route.getComponentPath());
        Meta meta=new Meta();
        if(!StringUtils.isEmpty(route.getCache())&&route.getCache().equals(0)){
            meta.setCache(true);
        }else{
            meta.setCache(false);
        }

        meta.setTitle(route.getTitle());
        childRoute.setMeta(meta);
        return childRoute;
    }

    private Set<String> getUserRoles(Set<Role> set){
        Set<String> result=new HashSet<>();
        for(Role role:set){
            result.add(role.getName());
        }
        return result;
    }

    private Set<String> getUserPermissions(Set<Role> set){
        Set<String> result=new HashSet<>();
        for(Role role:set){
            Set<Menu> pSets= role.getPermissions();
            for(Menu menu:pSets){
                if(menu.getLock().equals(0)&&menu.getType().equals(1)){
                    result.add(menu.getPermission());
                }
            }
        }
        return result;
    }

    private List<Menu> getAllMenu(Set<Role> set){
        List<Menu> menuList=new ArrayList<>();
        for(Role role:set){
            Set<Menu> pSets= role.getPermissions();
            menuList.addAll(pSets);
        }
        return menuList;
    }

    private List<MenuInfo> getUserMenu(Set<Role> set){
        List<MenuInfo> menuList=new ArrayList<>();
        for(Role roleUser:set){
            Set<Menu> pSets= roleUser.getPermissions();
            for(Menu menu:pSets){
                if(menu.getLock().equals(0)&&menu.getType().equals(1)){
                    MenuInfo menuInfo=new MenuInfo();
                    menuInfo.setIcon(menu.getIcon());
                    menuInfo.setPath(menu.getPath());
                    menuInfo.setTitle(menu.getTitle());
                    menuInfo.setSort(menu.getSort());
                    menuInfo.setId(menu.getId());
                    menuInfo.setParentId(menu.getParentId());
                    menuInfo.setChildren(new ArrayList<>());
                    menuList.add(menuInfo);
                }
            }
        }
        return TreeUtils.getMenuTreeList(menuList);
    }



    public User getUser(String userName) {
        return userRepository.findByName(userName);
    }

}
