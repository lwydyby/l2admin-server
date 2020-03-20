package com.example.cli.service;

import com.example.cli.constant.DeletedEnum;
import com.example.cli.constant.StatusEnum;
import com.example.cli.domain.common.Permission;
import com.example.cli.domain.common.RpInfo;
import com.example.cli.domain.search.BaseSearch;
import com.example.cli.domain.common.PageInfo;
import com.example.cli.domain.search.RoleSearch;
import com.example.cli.entity.Menu;
import com.example.cli.entity.Role;
import com.example.cli.entity.User;
import com.example.cli.repository.MenuRepository;
import com.example.cli.repository.RoleRepository;
import com.example.cli.utils.MyBeanUtils;
import com.example.cli.utils.PageUtils;
import com.example.cli.utils.RequestUserHolder;
import com.example.cli.utils.TreeUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author liwei
 * @title: RoleService
 * @projectName cli
 * @description: TODO
 * @date 2019-10-22 10:23
 */
@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    UserService userService;

    public PageInfo<Role> getAll(RoleSearch baseSearch) {
        Pageable pageable = PageRequest.of(baseSearch.getPageNo() - 1, baseSearch.getPageSize());
        Specification<Role> specification = (Specification<Role>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(!StringUtils.isEmpty(baseSearch.getName())){
                predicates.add(criteriaBuilder.equal(root.get("name"),baseSearch.getName()));
            }
            predicates.add(criteriaBuilder.equal(root.get("deleted"),DeletedEnum.NOT_DELETE));
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
        Page<Role> page=roleRepository.findAll(specification, pageable);
        for(Role role:page.getContent()){
            List<Permission> list=new ArrayList<>();

            userService.getPermission(TreeUtils.getMenuTreeList(role.getMenus()),list,role.getRoleId());
            role.setPermissions(list);
        }
        return PageUtils.getPageInfo(page,Role.class);

    }

    public List<Permission> getAllPermission(){
        List<Menu> menus=TreeUtils.getMenuTreeList(menuRepository.findAll());
        List<Permission> list=new ArrayList<>();
        userService.getPermission(menus,list,null);
        return list;
    }

    public Role getRole(Integer id){
        Role role= roleRepository.getOne(id);
        List<Permission> list=new ArrayList<>();
        userService.getPermission(role.getMenus(),list,role.getRoleId());
        role.setPermissions(list);
        return role;
    }

    public List<Role> getAll(){
        return roleRepository.findAllByDeletedAndStatus(DeletedEnum.NOT_DELETE,StatusEnum.USED);
    }

    public Role disableRole(Integer id){
        Role role= roleRepository.getOne(id);
        role.setStatus(StatusEnum.UNUSED);
        roleRepository.saveAndFlush(role);
        return role;
    }

    public Role enableRole(Integer id){
        Role role= roleRepository.getOne(id);
        role.setStatus(StatusEnum.USED);
        roleRepository.saveAndFlush(role);
        return role;
    }


    public void saveRole(Role role){
        User user= RequestUserHolder.getUser();
        if(!StringUtils.isEmpty(role.getPermissionIds())){
            List<Integer> menuList = role.getPermissionIds().stream().flatMap(Collection::stream)
                    .distinct().collect(Collectors.toList());
            Set<Integer> hasMenuId=new HashSet<>();
            List<Menu> menus=new ArrayList<>();
            for(Integer menuId:menuList){
                Menu menu=menuRepository.getOne(menuId);
                if(!hasMenuId.contains(menuId)){
                    menus.add(menu);
                    hasMenuId.add(menuId);
                }
                getParentMenu(menu,hasMenuId,menus);
            }
            role.setMenus(menus);
        }
        if(StringUtils.isEmpty(role.getId())){
            role.setCreateTime(new Date());
            role.setCreateUser(user);
            role.setDeleted(DeletedEnum.NOT_DELETE);
            roleRepository.save(role);
        }else {
            Role target=roleRepository.getOne(role.getId());
            MyBeanUtils.copyProperties(role,target);
            roleRepository.save(target);
        }
    }

    private void getParentMenu(Menu menu,Set<Integer> hasMenuId,List<Menu> menus){
        if(menu.getParentId()!=null){
            Menu parentMenu=menuRepository.getOne(menu.getParentId());
            if(!hasMenuId.contains(parentMenu.getId())){
                menus.add(parentMenu);
                hasMenuId.add(parentMenu.getId());
            }
            if(!StringUtils.isEmpty(parentMenu.getParentId())){
                getParentMenu(parentMenu,hasMenuId,menus);
            }
        }

    }

    public void delRole(Integer id){
        Role role= roleRepository.getOne(id);
        role.setDeleted(DeletedEnum.DELETE);
        roleRepository.delete(role);
    }





}
