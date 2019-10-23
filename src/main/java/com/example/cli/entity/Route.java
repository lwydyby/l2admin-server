package com.example.cli.entity;


import java.io.Serializable;


import java.util.Date;
import javax.persistence.*;

import com.example.cli.domain.BaseTree;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;


/**
 * @author lw
 * @email liwei4@chinatelecom.cn
 * @date 2019-10-21 11:26:39
 */
@Data
@Table(name = "route")
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Route extends BaseTree<Route> implements Serializable {
    private static final long serialVersionUID = 1L;


    /***/
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    /***/
    @Column(name = "parent_id")
    private String parentId;

    /***/
    @Column(name = "path")
    private String path;

    /***/
    @Column(name = "name")
    private String name;

    /***/
    @Column(name = "title")
    private String title;

    /***/
    @Column(name = "permission")
    private String permission;

    /***/
    @Column(name = "sort")
    private Integer sort;

    /***/
    @Column(name = "component")
    private String component;

    /***/
    @Column(name = "component_path")
    private String componentPath;

    /**
     * 0缓存 1不缓存
     * */
    @Column(name = "cache")
    private Integer cache;

    /***/
    @Column(name = "is_lock")
    private Integer lock;


}
