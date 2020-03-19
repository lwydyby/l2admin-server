package com.example.cli.controller;

import com.example.cli.domain.common.ResponseBean;
import com.example.cli.domain.search.BaseSearch;
import com.example.cli.domain.add.SavePermission;
import com.example.cli.domain.search.RoleSearch;
import com.example.cli.domain.search.UserRoleSearch;
import com.example.cli.entity.Role;
import com.example.cli.service.RoleService;
import com.example.cli.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author liwei
 * @title: RoleController
 * @projectName cli
 * @description: TODO
 * @date 2019-10-22 10:16
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @GetMapping
    public ResponseBean getRolePage(RoleSearch baseSearch){
        return new ResponseBean(roleService.getAll(baseSearch));
    }

    @GetMapping("/getAllPermission")
    public ResponseBean getAllPermission(){
        return new ResponseBean(roleService.getAllPermission());
    }


    @GetMapping("/{id}")
    public ResponseBean getRole(@PathVariable("id") Integer id){
        return new ResponseBean(roleService.getRole(id));
    }

    @DeleteMapping
    public ResponseBean delRole(@RequestParam("id")Integer id){
        roleService.delRole(id);
        return new ResponseBean("success");
    }

    @PutMapping("/enableRole")
    public ResponseBean enableRole(@RequestParam("id") Integer id){
        roleService.enableRole(id);
        return new ResponseBean("success");
    }

    @PutMapping("/disableRole")
    public ResponseBean disableRole(@RequestParam("id") Integer id){
        roleService.disableRole(id);
        return new ResponseBean("success");
    }

    @PostMapping
    public ResponseBean saveRole(@RequestBody Role role){
        roleService.saveRole(role);
        return new ResponseBean("success");
    }

    @GetMapping("/getAll")
    public ResponseBean getAll(){
        return new ResponseBean(roleService.getAll());
    }


}
