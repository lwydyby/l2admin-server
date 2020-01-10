package com.example.cli.valid.context.impl;

import com.example.cli.valid.context.IConditionContext;
import lombok.Builder;

import java.lang.reflect.Field;

/**
 * @author liwei
 * @title: DefaultConditionContext
 * @projectName sdkproxy
 * @description: TODO
 * @date 2020-01-02 17:27
 */
@Builder
public class DefaultConditionContext implements IConditionContext {

    private Object value;

    private Class[] group;

    private Object instance;

    private Double version;

    private Class[] validGroup;

    private Field field;

    @Override
    public Object value() {
        return value;
    }

    @Override
    public Class[] group() {
        return group;
    }

    @Override
    public Object instance() {
        return instance;
    }

    @Override
    public Double version() {
        return version;
    }

    @Override
    public Class[] validGroup() {
        return validGroup;
    }

    @Override
    public Field field() {
        return field;
    }
}
