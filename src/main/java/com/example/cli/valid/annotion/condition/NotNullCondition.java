package com.example.cli.valid.annotion.condition;

import com.example.cli.valid.annotion.NotNull;
import com.example.cli.valid.condition.AbstractAnnotationCondition;
import com.example.cli.valid.condition.ICondition;
import com.example.cli.valid.context.IConditionContext;
import com.example.cli.valid.utils.GroupUtils;
import org.springframework.util.StringUtils;

/**
 * @author liwei
 * @title: NotNullCondition
 * @projectName sdkproxy
 * @description: TODO
 * @date 2020-01-03 09:19
 */
public class NotNullCondition extends AbstractAnnotationCondition<NotNull> {
    @Override
    protected ICondition buildCondition(NotNull annotation) {
        return new NotNullConditionImpl(annotation);
    }

    static class NotNullConditionImpl implements ICondition{

        NotNull notNull;

        private NotNullConditionImpl(NotNull notNull) {
            this.notNull = notNull;
        }

        @Override
        public boolean condition(IConditionContext conditionContext) {
            double version=notNull.version();
            double validVersion=conditionContext.version();
            if(validVersion<version){
                return true;
            }
            Class[] group=notNull.group();
            if(group.length!=0){
                //校验分组
                if(!GroupUtils.isInGroup(group,conditionContext.validGroup())){
                    return true;
                }
            }
            return !StringUtils.isEmpty(conditionContext.value());
        }
    }
}
