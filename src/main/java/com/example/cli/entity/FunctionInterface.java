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
@Table(name = "function_interface")
@Entity
public class FunctionInterface implements Serializable {
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "function_id")
	private Menu menu;


	@ManyToOne
	@JoinColumn(name = "interface_id")
	private Interface anInterface;





}
