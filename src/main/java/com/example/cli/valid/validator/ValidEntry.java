package com.example.cli.valid.validator;

import com.example.cli.valid.condition.AbstractAnnotationCondition;
import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Field;

/**
 * @author liwei
 * @title: ValidEntry
 * @projectName sdkproxy
 * @description: TODO
 * @date 2020-01-02 17:49
 */
@Data
@Builder
public class ValidEntry {
    /**
     * 待验证的值
     */
    private Object value;


    /**
     * 约束对应的生效条件
     */
    private AbstractAnnotationCondition condition;

    /**
     * 约束对应的消息提示
     */
    private String message;

    /**
     * 分组信息
     */
    private Class[] group;

    /**
     * 对应的实例对象
     */
    private Object instance;

    /**
     * 对应的字段
     */
    private Field field;
}
