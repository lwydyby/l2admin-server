package com.example.cli.domain.common;


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
    private Integer id;
    @Transient
    private Integer parentId;
    @Transient
    private List<T> children;

    @Transient
    private Integer sort;
}
