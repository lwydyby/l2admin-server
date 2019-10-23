package com.example.cli.entity;


import java.io.Serializable;


import java.util.Date;
import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;


/**
 * @author lw
 * @email liwei4@chinatelecom.cn
 * @date 2019-10-21 11:26:40
 */
@Data
@Table(name = "role_user")
@Entity
public class RoleUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    /**
     *  2 未添加 1 已添加
     */
    @Column(name= "is_add")
    private Integer isAdd;

}
