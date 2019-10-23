package com.example.cli.service;

import com.example.cli.domain.BaseSearch;
import com.example.cli.domain.RolePermission;
import com.example.cli.domain.SavePermission;
import com.example.cli.entity.Menu;
import com.example.cli.entity.Permission;
import com.example.cli.entity.Role;
import com.example.cli.repository.MenuRepository;
import com.example.cli.repository.PermissionRepository;
import com.example.cli.repository.RoleRepository;
import com.example.cli.utils.MyBeanUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    PermissionRepository permissionRepository;

    @Autowired
    MenuRepository menuRepository;

    public Page<Role> getAll(BaseSearch baseSearch) {
        Pageable pageable = PageRequest.of(baseSearch.getPage() - 1, baseSearch.getLimit());
        Specification<Role> specification = (Specification<Role>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
        return roleRepository.findAll(specification, pageable);

    }

    public Role getRole(String id){
        return roleRepository.getOne(id);
    }

    public void saveRole(Role role){
        if(StringUtils.isEmpty(role.getId())){
            roleRepository.save(role);
        }else {
            Role target=roleRepository.getOne(role.getId());
            MyBeanUtils.copyProperties(role,target);
            roleRepository.save(target);
        }
    }

    public void delRole(String id){
        roleRepository.deleteById(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public void deleteRoleBatch(String ids) throws IOException {
        List<String> id=objectMapper.readValue(ids,new TypeReference<List<String>>(){});
        for(String s:id){
            roleRepository.deleteById(s);
        }
    }

    public List<RolePermission> getRolePermissions(String roleId){
        List<Permission> list=permissionRepository.findAllByRole_Id(roleId);
        List<RolePermission> result=new ArrayList<>();
        //移除根节点防止默认全选
        list.removeIf(p->StringUtils.isEmpty(p.getMenu().getParentId()));
        list.forEach(permission -> {
            RolePermission rolePermission=new RolePermission();
            rolePermission.setFunctionId(permission.getMenu().getId());
            rolePermission.setRoleId(permission.getRole().getId());
            result.add(rolePermission);
        });


        return result;
    }

    @Transactional(rollbackOn = Exception.class)
    public void savePermission(SavePermission savePermission){
        List<Permission> old=permissionRepository.findAllByRole_Id(savePermission.getRoleId());
        List<Permission> newPermission=new ArrayList<>();
        Role role=roleRepository.getOne(savePermission.getRoleId());
        Set<String> rootMenu=new HashSet<>();
        for(String id:savePermission.getPermissions()){
            Permission permission=new Permission();
            permission.setRole(role);
            Menu menu=menuRepository.getOne(id);
            //处理根节点不返回的问题
            if(!StringUtils.isEmpty(menu.getParentId())){
                rootMenu.add(menu.getParentId());
            }
            permission.setMenu(menu);
            newPermission.add(permission);
        }
        rootMenu.forEach(id->{
            Permission permission=new Permission();
            permission.setRole(role);
            permission.setMenu(menuRepository.getOne(id));
            newPermission.add(permission);
        });
        permissionRepository.deleteAll(old);
        permissionRepository.saveAll(newPermission);

    }
}
