package com.example.cli.domain.common;

import lombok.Data;

/**
 * @author liwei
 * @title: UserInfo
 * @projectName cli
 * @description: TODO
 * @date 2019-10-23 09:53
 */
@Data
public class UserInfo {

    private String value;

    private String label;

    public UserInfo(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public UserInfo() {
    }
}
