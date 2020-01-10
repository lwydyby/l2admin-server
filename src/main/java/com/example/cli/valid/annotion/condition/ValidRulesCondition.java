package com.example.cli.valid.annotion.condition;

import com.example.cli.valid.annotion.ValidRule;
import com.example.cli.valid.annotion.ValidType;
import com.example.cli.valid.condition.AbstractAnnotationCondition;
import com.example.cli.valid.condition.ICondition;
import com.example.cli.valid.context.IConditionContext;

import java.util.List;

/**
 * @author liwei
 * @title: ValidRulesCondition
 * @projectName sdkproxy
 * @description: TODO
 * @date 2020-01-07 09:40
 */
public class ValidRulesCondition extends AbstractAnnotationCondition<ValidRule> {
    @Override
    protected ICondition buildCondition(ValidRule annotation) {
        return new ValidRulesConditionImpl(annotation);
    }

    static class ValidRulesConditionImpl implements ICondition{

        ValidRule validRule;

        private ValidRulesConditionImpl(ValidRule validRules){
            this.validRule=validRules;
        }

        @Override
        public boolean condition(IConditionContext conditionContext) {
            Object value=conditionContext.value();
            @SuppressWarnings("unchecked")
            List<Object> list= (List<Object>) value;

            if(conditionContext.version()<validRule.version()){
                //低于最低版本，跳过校验
                return true;
            }
            if(list.size()<validRule.index()+1){
                //越界默认错误
                return false;
            }
            Object validValue=list.get(validRule.index());
            return valid(validValue, validRule.type());
        }

        private boolean valid(Object value, ValidType type){
            switch (type){
                case notNull:
                    return value!=null;
                default:
                    return true;
            }

        }
    }
}
