package com.example.cli.domain;


import lombok.Data;

import javax.persistence.Transient;
import java.util.List;

/**
 * @author liwei
 * @title: BaseTree
 * @projectName cli
 * @description: TODO
 * @date 2019-10-23 08:20
 */
@Data
public class BaseTree<T> {
    @Transient
    private String id;
    @Transient
    private String parentId;
    @Transient
    private List<T> children;

    @Transient
    private Integer sort;
}
