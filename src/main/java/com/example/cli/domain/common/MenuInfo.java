package com.example.cli.domain.common;

import lombok.Data;

/**
 * @author liwei
 * @title: MenuInfo
 * @projectName cli
 * @description: TODO
 * @date 2019-10-21 15:53
 */
@Data
public class MenuInfo extends BaseTree<MenuInfo> {

    private String title;

    private String path;

    private String icon;



}
