package com.example.cli.domain.search;

import com.example.cli.domain.search.BaseSearch;
import lombok.Data;

/**
 * @author liwei
 * @title: UserRoleSearch
 * @projectName cli
 * @description: TODO
 * @date 2019-10-22 09:59
 */
@Data
public class UserRoleSearch extends BaseSearch {

    private String name;

    private String code;

    private String userId;
}
