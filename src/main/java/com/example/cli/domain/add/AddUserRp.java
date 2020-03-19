package com.example.cli.domain.add;

import lombok.Data;

import java.util.List;

/**
 * @author liwei
 * @title: AddUserRp
 * @projectName cli
 * @description: TODO
 * @date 2019-10-23 10:34
 */
@Data
public class AddUserRp {

    private String userId;

    private List<Long> rpId;
}
