package com.example.cli.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 用于动态配置字段使用,暂不支持
 */
@Data
@Table(name = "field_info")
@Entity
public class FieldInfo {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "field_id")
    private Integer fieldId;

    @Column(name = "field_name")
    private String fieldName;

    @Column(name = "name")
    private String name;


    private Integer type;


    private Integer label;


    private String remark;

    @Column(name = "input_tips")
    private String inputTips;


    private Integer maxLength;

    @Column(name = "default_value")
    private String defaultValue;

    @Column(name = "is_unique")
    private Integer isUnique;

    @Column(name = "is_null")
    private Integer isNull;

    @Column(name = "sorting")
    private Integer sorting;


    private Integer operating;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "field_type")
    private Integer fieldType;


    private Integer relevant;


    private String options;

}
