package com.example.cli.domain;

import lombok.Data;

/**
 * @author liwei
 * @title: UserSearch
 * @projectName cli
 * @description: TODO
 * @date 2019-10-22 08:55
 */
@Data
public class UserSearch extends BaseSearch {

    private String name;

    private String email;

    private String roleId;
}
