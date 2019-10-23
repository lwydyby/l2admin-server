package com.example.cli.entity;


import java.io.Serializable;


import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;


/**
 * @author lw
 * @email liwei4@chinatelecom.cn
 * @date 2019-10-21 11:26:40
 */
@Data
@Table(name = "role")
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;


    /***/
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    /***/
    @Column(name = "name")
    private String name;

    /***/
    @Column(name = "code")
    private String code;

    /***/
    @Column(name = "description")
    private String description;



    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "permission",joinColumns = @JoinColumn(name="role_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "function_id",referencedColumnName = "id"))
    private Set<Menu> permissions;

    @Transient
    private Integer isAdd;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
