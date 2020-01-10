package com.example.cli.valid.result;

public interface IConstraintResult {

    /**
     * 是否通过约束限制
     * @return 是否
     */
    boolean pass();

    /**
     * 消息信息
     * @return 消息
     */
    String message();

    /**
     * 未通过的限制值
     * @return 结果
     */
    Object value();

    /**
     * 约束类信息
     * @return 约束类信息
     */
    String constraint();

    /**
     * 预期值
     * @return 预期值
     */
    String expectValue();
}
