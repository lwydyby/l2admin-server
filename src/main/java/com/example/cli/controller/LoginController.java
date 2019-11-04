package com.example.cli.controller;

import com.example.cli.domain.LoginForm;
import com.example.cli.domain.ResponseBean;
import com.example.cli.service.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    private ResponseBean layOut() {
        return new ResponseBean(200, "layout success", null);
    }

}
