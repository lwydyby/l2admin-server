package com.example.cli.valid.result.impl;

import com.example.cli.valid.exception.ValidRuntimeException;
import com.example.cli.valid.result.IConstraintResult;
import com.example.cli.valid.result.IResult;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author liwei
 * @title: DefaultResult
 * @projectName sdkproxy
 * @description: TODO
 * @date 2020-01-03 09:06
 */
@Data
@ToString
public class DefaultResult implements IResult {

    /**
     * 是否全部通过验证
     */
    private boolean pass;

    /**
     * 未通过的列表信息
     */
    private List<IConstraintResult> notPassList;

    /**
     * 所有的验证结果列表
     */
    private List<IConstraintResult> allList;

    @Override
    public boolean pass() {
        return pass;
    }

    @Override
    public List<IConstraintResult> notPassList() {
        return notPassList;
    }

    @Override
    public List<IConstraintResult> allList() {
        return allList;
    }

    @Override
    public IResult print() {
        System.out.println(this);
        return this;
    }

    @Override
    public IResult throwsEx() {
        if(!pass) {
            final String message = this.notPassList.get(0).message();
            throw new ValidRuntimeException(message);
        }
        return this;
    }
}
