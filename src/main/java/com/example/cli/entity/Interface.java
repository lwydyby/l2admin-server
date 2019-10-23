package com.example.cli.entity;


import java.io.Serializable;


import java.util.Date;
import java.util.Set;
import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;


/**
 * @author lw
 * @email liwei4@chinatelecom.cn
 * @date 2019-10-21 11:26:39
 */
@Data
@Table(name = "interface")
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Interface implements Serializable {
    private static final long serialVersionUID = 1L;


    /***/
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    /***/
    @Column(name = "name")
    private String name;

    /***/
    @Column(name = "path")
    private String path;

    /***/
    @Column(name = "method")
    private String method;

    /***/
    @Column(name = "description")
    private String description;

	@OneToMany(mappedBy = "anInterface", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<FunctionInterface> interfaces;

}
