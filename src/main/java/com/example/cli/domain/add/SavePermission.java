package com.example.cli.domain.add;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author liwei
 * @title: SavePermission
 * @projectName cli
 * @description: TODO
 * @date 2019-10-22 14:45
 */
@Data
public class SavePermission {

    private String roleId;

    private Set<String> permissions;
}
