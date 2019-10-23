package com.example.cli.controller;

import com.example.cli.domain.ResponseBean;
import com.example.cli.domain.*;
import com.example.cli.entity.User;
import com.example.cli.service.UserPermissionService;
import com.example.cli.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author liwei
 * @title: UserPermissionController
 * @projectName cli
 * @description: TODO
 * @date 2019-10-21 16:47
 */
@RestController
@RequestMapping("/user")
public class UserPermissionController {

    @Autowired
    private UserPermissionService permissionService;

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    @RequiresAuthentication
    public ResponseBean getPermissionInfo(){
        return new ResponseBean(permissionService.getPermissionInfo());
    }


    @GetMapping("pagedlist")
    public ResponseBean getUserPagedList(UserSearch baseSearch){
        return new ResponseBean(userService.getAll(baseSearch));
    }

    @GetMapping("/{id}")
    public ResponseBean getUserById(@PathVariable("id") String id){
        return new ResponseBean(userService.getUserById(id));
    }

    @PostMapping("/save")
    public ResponseBean saveUser(@RequestBody User user){
        userService.saveUser(user);
        return new ResponseBean("success");
    }

    @DeleteMapping("/del")
    public ResponseBean delUser(@RequestParam("id") String id){
        userService.deleteUser(id);
        return new ResponseBean("success");
    }

    @DeleteMapping("/batchdel")
    public ResponseBean delUserBatch(@RequestBody String ids) throws IOException {
        userService.deleteUserBatch(ids);
        return new ResponseBean("success");
    }

    @PostMapping("/editroleuser")
    public ResponseBean editRoleUser(@RequestBody RoleUserAction roleUserAction){
        userService.modifyRoleUser(roleUserAction);
        return new ResponseBean("success");
    }


    @GetMapping("/all")
    public ResponseBean getAll(){
        return new ResponseBean(userService.getAllUser());
    }


    @PostMapping("/addRole")
    public ResponseBean addRole(@RequestBody AddUserRole addUserRole){
        userService.addUserRole(addUserRole);
        return new ResponseBean("修改成功");
    }

}
