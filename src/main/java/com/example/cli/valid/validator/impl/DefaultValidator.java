package com.example.cli.valid.validator.impl;

import com.example.cli.valid.condition.AbstractAnnotationCondition;
import com.example.cli.valid.condition.Condition;
import com.example.cli.valid.constants.AnnotationConst;
import com.example.cli.valid.context.IConditionContext;
import com.example.cli.valid.context.impl.DefaultConditionContext;
import com.example.cli.valid.result.IConstraintResult;
import com.example.cli.valid.result.impl.DefaultConstraintResult;
import com.example.cli.valid.utils.ClassTypeUtil;
import com.example.cli.valid.utils.ObjectUtil;
import com.example.cli.valid.utils.ReflectAnnotationUtil;
import com.example.cli.valid.validator.IValidator;
import com.example.cli.valid.validator.ValidEntry;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultValidator implements IValidator {

    @Override
    public List<IConstraintResult> valid(Object value,Double version,Class[] group) {
        List<IConstraintResult> resultList = new ArrayList<>();
        List<ValidEntry> validatorEntryList=buildValidatorEntryList(value);
        for(ValidEntry validEntry:validatorEntryList){
            IConditionContext conditionContext= DefaultConditionContext.builder().group(validEntry.getGroup())
                    .instance(validEntry.getInstance()).value(validEntry.getValue()).version(version).validGroup(group)
                    .build();
            valid(validEntry,conditionContext,resultList);
        }
        return resultList;
    }
    /**
     * 返回message字符串替换
     * @param entry 条件上下文
     */
    protected void message(ValidEntry entry){
        if(!entry.getMessage().contains("%s")){
            return;
        }
        entry.setMessage(String.format(entry.getMessage(),entry.getField().getName()));
    }

    /**
     * 进行校验并处理结果信息
     * @param validEntry 校验的实体信息
     * @param conditionContext 校验上下文
     * @param resultList 结果集合
     */
    protected void valid(ValidEntry validEntry,IConditionContext conditionContext,List<IConstraintResult> resultList){
        boolean pass=validEntry.getCondition().condition(conditionContext);
        if(!pass){
            message(validEntry);
        }
        IConstraintResult constraintResult= DefaultConstraintResult.builder()
                .constraint(validEntry.getCondition().getClass().getName()).message(validEntry.getMessage())
                .pass(pass).value(validEntry.getValue()).build();

        resultList.add(constraintResult);
    }

    @Override
    public List<ValidEntry> buildValidatorEntryList(Object instance) {
        final Class clazz = instance.getClass();
        // 不处理的类型
        if (ClassTypeUtil.isMap(clazz)
                || ClassTypeUtil.isAbstractOrInterface(clazz)
                || ClassTypeUtil.isPrimitive(clazz)
                || ClassTypeUtil.isJdk(clazz)) {
            return Collections.emptyList();
        }
        Field[] fieldList=clazz.getDeclaredFields();
        if(fieldList.length==0){
            return Collections.emptyList();
        }
        List<ValidEntry> validatorEntryList = new ArrayList<>();
        for(Field field:fieldList){
            field.setAccessible(true);
            Annotation[] annotationList=field.getAnnotations();
            for(Annotation annotation:annotationList){
                Condition condition=annotation.annotationType().getAnnotation(Condition.class);
                if(condition==null){
                    //不是校验注解跳过
                    continue;
                }
                Class<? extends AbstractAnnotationCondition> annotationClazz = condition.value();
                try {
                    AbstractAnnotationCondition annotationCondition= annotationClazz.newInstance();
                    annotationCondition.initialize(annotation);
                    ValidEntry validEntry=ValidEntry.builder().condition(annotationCondition)
                            .group(getGroup(annotation)).instance(instance).value(field.get(instance))
                            .message(getMessage(annotation)).field(field).build();
                    validatorEntryList.add(validEntry);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        return validatorEntryList;
    }

    /**
     * 获取注解对应的 message 信息
     *
     * @param annotation 注解
     * @return message() 方法对应的值。
     */
    protected String getMessage(final Annotation annotation) {
        return ReflectAnnotationUtil.getValueStr(annotation, AnnotationConst.MESSAGE);
    }

    /**
     * 获取注解对应的 message 信息
     *
     * @param annotation 注解
     * @return message() 方法对应的值。
     */
    private Class[] getGroup(final Annotation annotation) {
        Object object = ReflectAnnotationUtil.getValue(annotation, AnnotationConst.GROUP);

        if (ObjectUtil.isNull(object)) {
            return new Class[0];
        }

        return (Class[]) object;
    }
}
