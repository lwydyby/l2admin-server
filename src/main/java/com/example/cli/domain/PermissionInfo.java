package com.example.cli.domain;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author liwei
 * @title: PermissionInfo
 * @projectName cli
 * @description: TODO
 * @date 2019-10-21 15:52
 */
@Data
public class PermissionInfo {

    private String userName;

    private Set<String> userRoles;

    private Set<String> userPermissions;

    private List<MenuInfo> accessMenus;

    private List<RouteInfo> accessRoutes;

    private Set<InterfaceInfo> accessInterfaces;

    private Integer isAdmin;

    private String avatarUrl;

    private Map<String,String> cols;

    private Map<String,String> orders;

    private List<RpInfo> allRp;

    private List<RpInfo> ownRp;
}
