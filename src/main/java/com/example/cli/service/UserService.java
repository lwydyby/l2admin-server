package com.example.cli.service;


import com.example.cli.exception.BaseException;
import com.example.cli.domain.*;
import com.example.cli.entity.Role;
import com.example.cli.entity.RoleUser;
import com.example.cli.entity.User;
import com.example.cli.repository.RoleRepository;
import com.example.cli.repository.RoleUserRepository;
import com.example.cli.repository.UserRepository;
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

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
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
    private RoleUserRepository roleUserRepository;
    @Autowired
    private RoleRepository roleRepository;

    public Page<User> getAll(UserSearch baseSearch) {
        Pageable pageable = PageRequest.of(baseSearch.getPage() - 1, baseSearch.getLimit());
        Specification<User> specification = (Specification<User>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isEmpty(baseSearch.getName())) {
                predicates.add(criteriaBuilder.equal(root.get("name"), baseSearch.getName()));
            }
            if (!StringUtils.isEmpty(baseSearch.getEmail())) {
                predicates.add(criteriaBuilder.equal(root.get("email"), baseSearch.getEmail()));
            }
            if (!StringUtils.isEmpty(baseSearch.getRoleId())) {
                SetJoin<User, Role> roleJoin = root.join(root.getModel().getSet("roles", Role.class), JoinType.LEFT);

                predicates.add(criteriaBuilder.equal(roleJoin.get("id").as(String.class),baseSearch.getRoleId()));
            }
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();

        };
        Page<User> page=userRepository.findAll(specification, pageable);
        if (!StringUtils.isEmpty(baseSearch.getRoleId())) {
            List<RoleUser> roleList=roleUserRepository.findAllByRole_id(baseSearch.getRoleId());
            Map<String,Integer> map=roleList.stream().collect(Collectors.toMap(i->i.getUser().getId(),RoleUser::getIsAdd));

            List<User> users=page.getContent();
            for(User user:users){
                user.setIsAdd(map.get(user.getId()));
            }
        }
        return page;

    }

    public Page<RoleUser> getAllRole(UserRoleSearch userRoleSearch){
        Pageable pageable = PageRequest.of(userRoleSearch.getPage() - 1, userRoleSearch.getLimit());
        Specification<RoleUser> specification = (Specification<RoleUser>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isEmpty(userRoleSearch.getName())) {
                predicates.add(criteriaBuilder.equal(root.join("role").get("name"), userRoleSearch.getName()));
            }
            if (!StringUtils.isEmpty(userRoleSearch.getCode())) {
                predicates.add(criteriaBuilder.equal(root.join("role").get("code"), userRoleSearch.getCode()));
            }

            predicates.add(criteriaBuilder.equal(root.join("user").get("id"),userRoleSearch.getUserId()));
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
        Page<RoleUser> page=roleUserRepository.findAll(specification,pageable);
        List<RoleUser> list=page.getContent();
        for(RoleUser roleUser:list){
            roleUser.getRole().setIsAdd(roleUser.getIsAdd());
        }
        return roleUserRepository.findAll(specification,pageable);
    }
    public void modifyRoleUser(RoleUserAction roleUserAction){
        RoleUser roleUser=roleUserRepository.findByUser_IdAndRole_Id(roleUserAction.getUserId(),roleUserAction.getRoleId());
        roleUser.setIsAdd(roleUserAction.getAction());
        roleUserRepository.save(roleUser);
    }


    public User getUserById(String id){
        return userRepository.getOne(id);
    }

    public void saveUser(User user){
        if(StringUtils.isEmpty(user.getId())){
            user.setIsAdmin(1);
            userRepository.save(user);
        }else {
            User oldUser=userRepository.getOne(user.getId());
            MyBeanUtils.copyProperties(user,oldUser);
            userRepository.save(oldUser);
        }

    }
    public void deleteUser(String id){
        userRepository.deleteById(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public void deleteUserBatch(String ids) throws IOException {
       List<String> id=objectMapper.readValue(ids,new TypeReference<List<String>>(){});
       for(String s:id){
           userRepository.deleteById(s);
       }
    }

    public List<UserInfo> getAllUser(){
        List<User> list=userRepository.findAll();
        List<UserInfo> result=new ArrayList<>();
        list.forEach(u->{
            result.add(new UserInfo(u.getId(),u.getTrueName()));
        });
        return result;
    }

    public void addUserRole(AddUserRole addUserRole){
        RoleUser check=roleUserRepository.findByUser_IdAndRole_Id(addUserRole.getUserId(),addUserRole.getRoleId());
        if(check!=null){
            throw new BaseException("该用户已赋予过该角色");
        }
        RoleUser roleUser=new RoleUser();
        roleUser.setIsAdd(0);
        roleUser.setRole(roleRepository.getOne(addUserRole.getRoleId()));
        roleUser.setUser(userRepository.getOne(addUserRole.getUserId()));
        roleUserRepository.save(roleUser);
    }





}
