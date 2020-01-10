package com.example.cli.valid.context;

import java.lang.reflect.Field;

/**
 * 校验的上下文
 */
public interface IConditionContext {
    /**
     * 待验证的值
     * @return 待验证的值
     */
    Object value();

    /**
     * 约束类分组信息
     * @return 分组信息
     */
    Class[] group();

    /**
     * 待验证的对象实例
     * @return 待验证的对象实例
     */
    Object instance();

    /**
     * 待验证的版本号
     * @return 待验证的版本号
     */
    Double version();

    /**
     * 待验证的分组信息
     * @return 待验证的分组信息
     */
    Class[] validGroup();

    /**
     * 待校验的field
     * @return 待校验的field
     */
    Field field();
}
