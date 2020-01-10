package com.example.cli.valid.annotion.condition;


import com.example.cli.exception.ValidException;
import com.example.cli.valid.annotion.SetNull;
import com.example.cli.valid.condition.AbstractAnnotationCondition;
import com.example.cli.valid.condition.ICondition;
import com.example.cli.valid.context.IConditionContext;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

/**
 * @author liwei
 * @title: NotNullCondition
 * @projectName sdkproxy
 * @description: TODO
 * @date 2020-01-03 09:19
 */
public class SetNullCondition extends AbstractAnnotationCondition<SetNull> {
    @Override
    protected ICondition buildCondition(SetNull annotation) {
        return new NotNullConditionImpl(annotation);
    }

    static class NotNullConditionImpl implements ICondition{

        SetNull setNull;

        private NotNullConditionImpl(SetNull setNull) {
            this.setNull = setNull;
        }

        @Override
        public boolean condition(IConditionContext conditionContext) {
            double version= setNull.version();
            double validVersion=conditionContext.version();
            if(validVersion<version){
                return true;
            }
            if(!StringUtils.isEmpty(conditionContext.value())){
                //如果存在则清除数据
                try {
                    conditionContext.field().set(conditionContext.instance(),null);
                } catch (IllegalAccessException e) {
                   throw new ValidException(HttpStatus.BAD_REQUEST);
                }
            }

            return true;
        }
    }
}
