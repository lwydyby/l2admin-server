package com.example.cli.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Transient;
import java.util.List;
import java.util.Set;

/**
 * @author liwei
 * @title: RouteInfo
 * @projectName cli
 * @description: TODO
 * @date 2019-10-21 15:54
 */
@Data
public class RouteInfo extends BaseTree<RouteInfo>{

    private String name;

    private String path;

    private String component;

    private String componentPath;

    private Meta meta;

    @JsonIgnore
    private String id;
    @JsonIgnore
    private String parentId;

    private List<RouteInfo> children;


}
