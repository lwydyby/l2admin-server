package com.example.cli.service;


import com.example.cli.constant.DeletedEnum;
import com.example.cli.constant.StatusEnum;
import com.example.cli.domain.add.AddUserRole;
import com.example.cli.domain.common.ActionEntrySet;
import com.example.cli.domain.common.PageInfo;
import com.example.cli.domain.common.Permission;
import com.example.cli.domain.search.UserRoleSearch;
import com.example.cli.domain.search.UserSearch;
import com.example.cli.entity.Menu;
import com.example.cli.entity.Role;
import com.example.cli.entity.Route;
import com.example.cli.entity.User;
import com.example.cli.repository.RoleRepository;
import com.example.cli.repository.UserRepository;
import com.example.cli.utils.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sun.security.provider.MD5;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.SetJoin;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liwei
 * @title: UserService
 * @projectName cli
 * @description: TODO
 * @date 2019-10-22 08:59
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RoleRepository roleRepository;

    public PageInfo<User> getAll(UserSearch baseSearch) {
        Pageable pageable = PageRequest.of(baseSearch.getPageNo() - 1, baseSearch.getPageSize());
        Specification<User> specification = (Specification<User>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isEmpty(baseSearch.getName())) {
                predicates.add(criteriaBuilder.equal(root.get("name"), baseSearch.getName()));
            }
            if (!StringUtils.isEmpty(baseSearch.getEmail())) {
                predicates.add(criteriaBuilder.equal(root.get("email"), baseSearch.getEmail()));
            }
            predicates.add(criteriaBuilder.equal(root.get("deleted"), DeletedEnum.NOT_DELETE));
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
        return PageUtils.getPageInfo(userRepository.findAll(specification, pageable));

    }




    public User getInfo() {
        User user = RequestUserHolder.getUser();
        Role role = user.getRole();
        if (role == null) {
            return user;
        }
        List<Menu> menus = role.getMenus();
        //获取树形结构的menu信息
        List<Menu> treeMenus = TreeUtils.getMenuTreeList(menus);
        List<Permission> permissions = new ArrayList<>();
        getPermission(treeMenus, permissions, role.getRoleId());
        role.setPermissions(permissions);
        return user;
    }

    public void getPermission(List<Menu> menus, List<Permission> permissions, String roleId) {
        if (menus == null || menus.size() == 0) {
            return;
        }
        if(menus.get(0).getType().equals(2)){
            return;
        }
        for (Menu menu : menus) {
            List<Menu> child = menu.getChildren();
            Permission permission = new Permission();
            permission.setRoleId(roleId);
            permission.setPermissionId(menu.getPermissionId());
            permission.setPermissionName(menu.getPermissionName());
            if (child != null && child.size() != 0) {
                if (child.get(0).getType().equals(2)) {
                    //功能的话
                    permission.setActionEntrySet(getAction(menu.getChildren()));
                } else {
                    //菜单
                    getPermission(menu.getChildren(), permissions, roleId);
                }
            }
            permissions.add(permission);
        }

    }


    public List<Route> getCurrentUserNav() {
        User user = RequestUserHolder.getUser();
        Role role = user.getRole();
        if (role == null) {
            return null;
        }

        List<Menu> menus = role.getMenus();
        //只要菜单的route
        return menus.stream().filter(menu -> menu.getType().equals(1))
                .map(Menu::getRoute)
                .collect(Collectors.toList());
    }




    public User getUserById(Integer id) {
        return userRepository.getOne(id);
    }

    public void saveUser(User user) {
        User useUser=RequestUserHolder.getUser();
        if(!StringUtils.isEmpty(user.getRoleId())){
            Role role=roleRepository.getOne(user.getRoleId());
            user.setRole(role);
        }
        if(StringUtils.isEmpty(user.getId())){
            user.setPassword(MD5Utils.stringToMD5(user.getPassword()));
            user.setCreateTime(new Date());
            user.setCreateUser(useUser);
            user.setDeleted(DeletedEnum.NOT_DELETE);
            userRepository.saveAndFlush(user);
        }else {
            User old=userRepository.getOne(user.getId());
            MyBeanUtils.copyProperties(user,old);
            userRepository.saveAndFlush(old);
        }

    }

    public void deleteUser(Integer id) {
        User user=userRepository.getOne(id);
        user.setDeleted(DeletedEnum.DELETE);
        userRepository.saveAndFlush(user);
    }

    public void disableUser(Integer id){
        User user=userRepository.getOne(id);
        user.setStatus(StatusEnum.UNUSED);
        userRepository.saveAndFlush(user);

    }

    public void enableUser(Integer id){
        User user=userRepository.getOne(id);
        user.setStatus(StatusEnum.USED);
        userRepository.saveAndFlush(user);

    }



    public void addUserRole(AddUserRole addUserRole) {
        User user=userRepository.getOne(addUserRole.getUserId());
        Role role=roleRepository.getOne(addUserRole.getRoleId());
        user.setRole(role);
        userRepository.saveAndFlush(user);
    }

    private List<ActionEntrySet> getAction(List<Menu> menus) {
        List<ActionEntrySet> actionEntrySets = new ArrayList<>();
        for (Menu menu : menus) {
            actionEntrySets.add(ActionEntrySet.builder()
                    .id(menu.getId())
                    .action(menu.getPermissionId())
                    .describe(menu.getPermissionName())
                    .defaultCheck(menu.getDefaultCheck()).build());
        }
        return actionEntrySets;
    }


}
