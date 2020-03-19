package com.example.cli.controller;

import com.example.cli.domain.common.LoginForm;
import com.example.cli.domain.common.ResponseBean;
import com.example.cli.service.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserPermissionService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseBean login(@RequestBody LoginForm loginForm) {
        return userService.login(loginForm);
    }



    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    private ResponseBean layOut(@RequestHeader("Access-Token") String token) {
        userService.logout(token);
        return new ResponseBean(200, "layout success", null);
    }

}
