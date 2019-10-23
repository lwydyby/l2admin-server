package com.example.cli.entity;




import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


/**
 * @author lw
 * @email liwei4@chinatelecom.cn
 * @date 2019-10-21 11:26:40
 */
@Data
@Table(name = "user")
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;


    /***/
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    /***/
    @Column(name = "name")
    private String name;

    /**
     * 密码
     */
    @Column(name = "password")
    @JsonIgnore
    private String password;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 手机号
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 名称
     */
    @Column(name = "true_name")
    private String trueName;

    /**
     * 头像
     */
    @Column(name = "avatar_url")
    private String avatarUrl;

    /**
     * 是否是管理员 0 是 1 不是
     */
    @Column(name = "is_admin")
    private Integer isAdmin;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "role_user",joinColumns = @JoinColumn(name="user_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    @JsonIgnore
    private Set<Role> roles;


    @Transient
    private Integer isAdd;
}
