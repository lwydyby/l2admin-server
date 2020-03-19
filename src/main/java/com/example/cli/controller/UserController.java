package com.example.cli.controller;

import com.example.cli.domain.add.AddUserRole;
import com.example.cli.domain.common.ResponseBean;
import com.example.cli.domain.search.UserSearch;
import com.example.cli.entity.User;
import com.example.cli.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liwei
 * @title: UserPermissionController
 * @projectName cli
 * @description: TODO
 * @date 2019-10-21 16:47
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public ResponseBean getInfo(){
        return new ResponseBean(userService.getInfo());
    }

    @GetMapping("/nav")
    public ResponseBean getCurrentUserNav(){
        return new ResponseBean(userService.getCurrentUserNav());
    }


    @GetMapping
    public ResponseBean getUserList(UserSearch search){
        return new ResponseBean(userService.getAll(search));
    }

    @DeleteMapping
    public ResponseBean delUser(@RequestParam("id")Integer id){
        userService.deleteUser(id);
        return new ResponseBean("success");
    }

    @PutMapping("/enableUser")
    public ResponseBean enableUser(@RequestParam("id") Integer id){
        userService.enableUser(id);
        return new ResponseBean("success");
    }

    @PutMapping("/disableUser")
    public ResponseBean disableUser(@RequestParam("id") Integer id){
        userService.disableUser(id);
        return new ResponseBean("success");
    }

    @PutMapping("/addUserRole")
    public ResponseBean addUserRole(@RequestBody AddUserRole addUserRole){
        userService.addUserRole(addUserRole);
        return new ResponseBean("success");
    }

    @PostMapping
    public ResponseBean saveUser(@RequestBody User user){
        userService.saveUser(user);
        return new ResponseBean("success");
    }


}
