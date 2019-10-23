package com.example.cli.domain;

import lombok.Data;

import java.util.Set;

/**
 * @author liwei
 * @title: RouteTree
 * @projectName cli
 * @description: TODO
 * @date 2019-10-22 11:11
 */
@Data
public class RouteTree extends BaseTree<RouteTree> {

    private String label;

    private String id;

}
