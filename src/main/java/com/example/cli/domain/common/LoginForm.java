package com.example.cli.domain.common;

import lombok.Data;

/**
 * @author liwei
 * @title: LoginForm
 * @projectName luwu
 * @description: TODO
 * @date 2019-10-21 18:07
 */
@Data
public class LoginForm {

    private String username;

    private String password;

    private String mobile;

    private String captcha;

    private String rememberMe;
    // login type: 0 email, 1 username, 2 telephone
    private Integer loginType;
}
