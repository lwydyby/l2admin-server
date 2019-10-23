package com.example.cli.entity;


import java.io.Serializable;


import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

import com.example.cli.domain.BaseTree;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;


/**
 * @author lw
 * @email liwei4@chinatelecom.cn
 * @date 2019-10-21 11:26:39
 */
@Data
@Table(name = "menu")
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Menu extends BaseTree<Menu> implements Serializable  {
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
    @Column(name = "title")
    private String title;

    /***/
    @Column(name = "icon")
    private String icon;

    /***/
    @Column(name = "permission")
    private String permission;

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


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "function_interface",joinColumns = @JoinColumn(name="function_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "interface_id",referencedColumnName = "id"))
    @JsonIgnore
    private Set<Interface> interfaces;

}
