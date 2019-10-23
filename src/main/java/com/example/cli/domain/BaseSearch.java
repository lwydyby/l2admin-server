package com.example.cli.domain;

import lombok.Data;

/**
 * @author liwei
 * @title: BaseQuery
 * @projectName cmdb
 * @description: TODO
 * @date 2019-10-09 13:42
 */
@Data
public class BaseSearch {

    private Integer page;

    private Integer limit;

    private Long resource_pool;


}
