package com.example.cli.valid.result.handler;

import com.example.cli.valid.result.IConstraintResult;
import com.example.cli.valid.result.IResult;
import com.example.cli.valid.result.handler.Impl.BaseResultHandler;

import java.util.List;

/**
 * @author liwei
 * @title: IResultHandler
 * @projectName sdkproxy
 * @description: 结果处理
 * @date 2020-01-03 09:03
 */
public interface IResultHandler<T> {
    /**
     * 对约束结果进行统一处理
     * @param constraintResultList 约束结果列表
     * @return 结果
     */
    T handle(final List<IConstraintResult> constraintResultList);

    static IResultHandler<IResult> simple(){
        return new BaseResultHandler();
    }
}
