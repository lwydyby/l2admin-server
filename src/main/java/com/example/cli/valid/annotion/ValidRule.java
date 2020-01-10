package com.example.cli.valid.annotion;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRule {

    /**
     * 字段索引 默认不用赋值
     * @return 字段索引
     */
    int index() default 0;

    /**
     * 校验类型
     * @return 校验类型
     */
    ValidType type();

    /**
     * 起始的版本信息
     * @return 起始的版本信息
     */
    double version();

    /**
     * 异常信息
     * @return 异常信息
     */
    String message();

    /**
     * 字段描述
     */
    String name();

    /**
     * 字段类型
     * @return 字段类型
     */
    Class classType() default String.class;
}
