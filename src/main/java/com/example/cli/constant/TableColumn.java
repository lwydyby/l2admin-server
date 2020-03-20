package com.example.cli.constant;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TableColumn {

    String title();

    String width() default "";

    String scopeSlots() default "";

    int sort() default 1;

}
