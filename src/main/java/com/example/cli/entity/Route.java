package com.example.cli.entity;


import java.io.Serializable;


import javax.persistence.*;

import com.example.cli.domain.common.BaseTree;
import lombok.*;


/**
 * @author lw
 * @email liwei4@chinatelecom.cn
 * @date 2019-10-21 11:26:39
 */
@Data
@Table(name = "route")
@Entity
public class Route extends BaseTree<Route> implements Serializable {
    private static final long serialVersionUID = 1L;


    /***/
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    /***/
    @Column(name = "parent_id")
    private Integer parentId;

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

    @OneToOne(mappedBy = "route",fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Menu menu;


}
