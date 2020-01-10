package com.example.cli.valid.validator.impl;

import com.example.cli.valid.annotion.ValidRule;
import com.example.cli.valid.annotion.ValidRules;
import com.example.cli.valid.annotion.condition.ValidRulesCondition;
import com.example.cli.valid.context.IConditionContext;
import com.example.cli.valid.context.impl.DefaultConditionContext;
import com.example.cli.valid.result.IConstraintResult;
import com.example.cli.valid.utils.ReflectAnnotationUtil;
import com.example.cli.valid.validator.ValidEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liwei
 * @title: ValidRulesValidator
 * @projectName sdkproxy
 * @description: TODO
 * @date 2020-01-07 10:02
 */
public class ValidRulesValidator extends DefaultValidator {

    public List<IConstraintResult> valid(Object value, Double version, ValidRules validRules){
        List<IConstraintResult> resultList = new ArrayList<>();
        List<ValidEntry> validatorEntryList=buildValidatorEntryList(validRules,value);
        for(ValidEntry validEntry:validatorEntryList){
            IConditionContext conditionContext= DefaultConditionContext.builder()
                    .instance(validEntry.getInstance()).value(validEntry.getValue()).version(version)
                    .build();
            valid(validEntry,conditionContext,resultList);
        }
        return resultList;
    }


    public List<ValidEntry> buildValidatorEntryList(ValidRules instance,Object value) {
        List<ValidEntry> validatorEntryList = new ArrayList<>();
        ValidRule [] rules=instance.rules();
        int index=0;
        for(ValidRule rule:rules){
            ReflectAnnotationUtil.updateValue(rule,"index",index);
            ValidRulesCondition validRulesCondition=new ValidRulesCondition();
            validRulesCondition.initialize(rule);
            ValidEntry validEntry=ValidEntry.builder().message(rule.message()).value(value)
                    .instance(value).condition(validRulesCondition).build();
            validatorEntryList.add(validEntry);
            index++;

        }
        return validatorEntryList;
    }
}
