package com.example.cli.entity;


import java.io.Serializable;


import java.util.Date;
import java.util.List;
import javax.persistence.*;

import com.example.cli.constant.DeletedEnum;
import com.example.cli.constant.StatusEnum;
import com.example.cli.constant.TableColumn;
import com.example.cli.domain.common.Permission;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


/**
 * @author lw
 * @email liwei4@chinatelecom.cn
 * @date 2019-10-21 11:26:40
 */
@Data
@Table(name = "role")
@Entity
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;


    /***/
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @TableColumn(title = "唯一识别码",sort = 1)
    private Integer id;

    /***/
    @Column(name = "name")
    @TableColumn(title = "角色名称",sort = 2)
    private String name;

    /***/
    @Column(name = "role_id")
    private String roleId;

    /***/
    @Column(name = "description")
    private String describe;


    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name = "create_id")
    @JsonIgnore
    private User createUser;

    @Column(name = "create_time")
    @TableColumn(title = "创建时间",scopeSlots = "createTime",sort = 4)
    private Date createTime;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "menu_role",joinColumns = @JoinColumn(name="role_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "function_id",referencedColumnName = "id"))
    @JsonIgnore
    private List<Menu> menus;


    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JsonIgnore
    private List<User> users;

    /**
     * 0 使用 1禁用
     */
    @Enumerated(EnumType.ORDINAL)
    @TableColumn(title = "状态",sort = 3)
    private StatusEnum status;

    /**
     * 0 未删除 1 已删除
     */
    @Enumerated(EnumType.ORDINAL)
    private DeletedEnum deleted;

    @Transient
    private List<Permission>  permissions;

    @Transient
    private List<List<Integer>> permissionIds;
}
