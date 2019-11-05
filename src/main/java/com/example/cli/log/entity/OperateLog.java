package com.example.cli.log.entity;


import com.example.cli.log.DataName;
import lombok.Data;

import javax.persistence.*;


/**
 * @author WWMXD
 */
@Data
@Table(name = "operate_log")
@Entity
public class OperateLog {
    private static final long serialVersionUID = 1L;
    //
    @DataName(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @DataName(name = "操作人")
    @Column(name = "username")
    private String username;

    //操作日期
    @DataName(name = "操作日期")
    @Column(name = "modify_date")
    private String modifyDate;

    //操作名词
    @DataName(name = "操作名词")
    @Column(name = "modify_name")
    private String modifyName;

    //操作对象
    @DataName(name = "操作对象")
    @Column(name = "modify_object")
    private String modifyObject;

    //操作内容

    @DataName(name = "操作内容")
    @Column(name = "modify_content")
    private String modifyContent;

    //ip

    @DataName(name = "IP")
    @Column(name = "modify_ip")
    private String modifyIp;



}