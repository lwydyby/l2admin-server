package com.example.cli.entity;


import java.io.Serializable;


import java.util.Date;
import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;


/**
 * @author lw
 * @email liwei4@chinatelecom.cn
 * @date 2019-10-21 11:26:39
 */
@Data
@Table(name = "permission")
@Entity
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;


    @ManyToOne
    @JoinColumn(name = "function_id")
    private Menu menu;



}
