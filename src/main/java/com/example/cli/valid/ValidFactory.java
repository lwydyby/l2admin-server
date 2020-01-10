package com.example.cli.valid;

import com.example.cli.valid.annotion.ValidRules;
import com.example.cli.valid.result.IConstraintResult;
import com.example.cli.valid.result.IResult;
import com.example.cli.valid.result.handler.IResultHandler;
import com.example.cli.valid.validator.impl.DefaultValidator;
import com.example.cli.valid.validator.impl.ValidRulesValidator;
import lombok.Data;

import java.util.List;

/**
 * @author liwei
 * @title: ValidFactory
 * @projectName sdkproxy
 * @description: TODO
 * @date 2020-01-02 17:41
 */
@Data
public class ValidFactory {

    /**
     * 分组信息
     */
    private Class[] group;
     /**
     * 对象信息
     */
    private Object value;

    /**
     * 约束验证结果列表
     */
    private List<IConstraintResult> constraintResults;
    /**
     * 版本信息
     */
    private Double version;

    /**
     * 是否已经执行验证
     */
    private volatile boolean validated = false;

    public static ValidFactory on(final Object value,final Double version,final Class[] group){
        ValidFactory factory=new ValidFactory();
        factory.setValue(value);
        factory.setVersion(version);
        factory.setGroup(group);
        return factory;
    }

    public ValidFactory valid(){
        DefaultValidator defaultValidator=new DefaultValidator();
        this.constraintResults=defaultValidator.valid(this.value,this.version,this.group);
        return this;
    }

    public ValidFactory valid(ValidRules rules){
        ValidRulesValidator validRulesValidator=new ValidRulesValidator();
        this.constraintResults=validRulesValidator.valid(value,version,rules);
        return this;
    }

    public IResult result(){
        return IResultHandler.simple().handle(this.constraintResults);
    }
}
