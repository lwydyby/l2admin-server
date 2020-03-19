package com.example.cli.domain.common;

import com.example.cli.domain.common.ActionEntrySet;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Permission {

    private String roleId;

    private String permissionId;

    private String permissionName;

    private List<ActionEntrySet> actionEntrySet=new ArrayList<>();
}
