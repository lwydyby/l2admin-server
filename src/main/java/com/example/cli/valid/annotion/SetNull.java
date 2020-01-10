package com.example.cli.valid.annotion;

import com.example.cli.valid.annotion.condition.SetNullCondition;
import com.example.cli.valid.condition.Condition;

import java.lang.annotation.*;

@Inherited
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Condition(SetNullCondition.class)
public @interface SetNull {
    /**
     * 校验的起始版本号
     * @return 版本号
     */
    double version() default 3.0;

    String message() default "";

}
