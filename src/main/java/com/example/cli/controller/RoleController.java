package com.example.cli.controller;

import com.example.cli.domain.ResponseBean;
import com.example.cli.domain.BaseSearch;
import com.example.cli.domain.SavePermission;
import com.example.cli.domain.UserRoleSearch;
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

    @GetMapping("/pagedlist")
    public ResponseBean getRolePagedList(UserRoleSearch userRoleSearch){
        return new ResponseBean(userService.getAllRole(userRoleSearch));
    }

    @GetMapping("/page")
    public ResponseBean getRolePage(BaseSearch baseSearch){
        return new ResponseBean(roleService.getAll(baseSearch));
    }

    @GetMapping("/{id}")
    public ResponseBean getRole(@PathVariable String id){
        return new ResponseBean(roleService.getRole(id));
    }

    @DeleteMapping("/del")
    public ResponseBean delRole(@RequestParam("id")String id){
        roleService.delRole(id);
        return new ResponseBean("success");
    }
    @DeleteMapping("/batchdel")
    public ResponseBean delRoleBatch(@RequestBody String ids) throws IOException {
        roleService.deleteRoleBatch(ids);
        return new ResponseBean("success");
    }

    @PostMapping("/save")
    public ResponseBean saveRole(@RequestBody Role role){
        roleService.saveRole(role);
        return new ResponseBean("success");
    }

    @GetMapping("/getpermissions/{id}")
    public ResponseBean getRolePermissions(@PathVariable("id")String roleId){
        return new ResponseBean(roleService.getRolePermissions(roleId));
    }

    @PostMapping("/savepermission")
    public ResponseBean savePermission(@RequestBody SavePermission savePermission){
        roleService.savePermission(savePermission);
        return new ResponseBean("success");
    }
}
