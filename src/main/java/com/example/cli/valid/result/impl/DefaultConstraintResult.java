package com.example.cli.valid.result.impl;

import com.example.cli.valid.result.IConstraintResult;
import lombok.Builder;

/**
 * @author liwei
 * @title: DefaultConstraintResult
 * @projectName sdkproxy
 * @description: TODO
 * @date 2020-01-02 17:39
 */
@Builder
public class DefaultConstraintResult implements IConstraintResult {

    /**
     * 是否通过约束限制
     *
     */
    private boolean pass;

    /**
     * 消息信息
     *
     */
    private String message;

    /**
     * 未通过的限制值
     *
     */
    private Object value;

    /**
     * 约束类信息
     *
     */
    private String constraint;

    /**
     * 预期值
     */
    private String expectValue;


    @Override
    public boolean pass() {
        return pass;
    }

    public DefaultConstraintResult pass(boolean pass) {
        this.pass = pass;
        return this;
    }

    @Override
    public String message() {
        return message;
    }

    public DefaultConstraintResult message(String message) {
        this.message = message;
        return this;
    }

    @Override
    public Object value() {
        return value;
    }

    public DefaultConstraintResult value(Object value) {
        this.value = value;
        return this;
    }

    @Override
    public String constraint() {
        return constraint;
    }

    public DefaultConstraintResult constraint(String constraint) {
        this.constraint = constraint;
        return this;
    }

    @Override
    public String expectValue() {
        return expectValue;
    }

    public DefaultConstraintResult expectValue(String expectValue) {
        this.expectValue = expectValue;
        return this;
    }

    @Override
    public String toString() {
        return "DefaultConstraintResult{" +
                "pass=" + pass +
                ", message='" + message + '\'' +
                ", value=" + value +
                ", constraint='" + constraint + '\'' +
                ", expectValue='" + expectValue + '\'' +
                '}';
    }

}
