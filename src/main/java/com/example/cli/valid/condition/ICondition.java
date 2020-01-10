package com.example.cli.valid.condition;

import com.example.cli.valid.context.IConditionContext;

public interface ICondition {
    /**
     * 条件校验的结果
     * @param conditionContext 条件上下文
     * @return 结果
     */
    boolean condition(final IConditionContext conditionContext);


}
