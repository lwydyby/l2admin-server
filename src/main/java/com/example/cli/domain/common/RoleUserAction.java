package com.example.cli.domain.common;

import lombok.Data;

/**
 * @author liwei
 * @title: RoleUserAction
 * @projectName cli
 * @description: TODO
 * @date 2019-10-22 10:57
 */
@Data
public class RoleUserAction {

    private String roleId;

    private String userId;

    private Integer action;
}
