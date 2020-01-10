package com.example.cli.valid.annotion;

import com.example.cli.valid.annotion.condition.ValidRulesCondition;
import com.example.cli.valid.condition.Condition;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Condition(ValidRulesCondition.class)
public @interface ValidRules {
    ValidRule[] rules();

    Class[] value() default {};


}
