package com.example.cli.entity;


import java.io.Serializable;


import java.util.Set;
import javax.persistence.*;

import com.example.cli.domain.common.BaseTree;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


/**
 * @author lw
 * @email liwei4@chinatelecom.cn
 * @date 2019-10-21 11:26:39
 */
@Data
@Table(name = "menu")
@Entity
public class Menu extends BaseTree<Menu> implements Serializable  {
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
    @Column(name = "title")
    private String title;

    /***/
    @Column(name = "icon")
    private String icon;

    /***/
    @Column(name = "permission_id")
    private String permissionId;

    @Column(name = "permission_name")
    private String permissionName;

    @Column(name = "default_check")
    private Boolean defaultCheck;

    /**
     * 类型 1 菜单 2 功能
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 是否锁定 0 未锁定 1 锁定
     */
    @Column(name = "is_lock")
    private Integer lock;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "route_id")
    @JsonIgnoreProperties({"menu"})
    private Route route;


    @OneToMany(mappedBy = "menu",fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonIgnore
    private Set<Interface> interfaces;

    @ManyToMany(mappedBy = "menus")
    @JsonIgnore
    private Set<Role> roles;

}
