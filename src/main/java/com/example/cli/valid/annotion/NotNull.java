package com.example.cli.valid.annotion;

import com.example.cli.valid.annotion.condition.NotNullCondition;
import com.example.cli.valid.condition.Condition;

import java.lang.annotation.*;

@Inherited
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Condition(NotNullCondition.class)
public @interface NotNull {
    /**
     * 校验的起始版本号
     * @return 版本号
     */
    double version() default 3.0;

    /**
     * 校验的分组
     * @return 校验的分组
     */
    Class[] group() default {};

    String message() default "%s Must Not NUll";
}
