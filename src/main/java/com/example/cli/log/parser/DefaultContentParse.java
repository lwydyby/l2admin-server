package com.example.cli.log.parser;


import com.example.cli.log.EnableModifyLog;
import com.example.cli.log.service.ILogService;
import com.example.cli.utils.SpringUtil;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * 基础解析类
 * 单表编辑时可以直接使用id来查询
 * 如果为多表复杂逻辑，请自行编写具体实现类
 * @author lw
 */
public class DefaultContentParse implements ContentParser {
    @Override
    public Object getResult(Map<String,Object> fieldValues, EnableModifyLog enableModifyLog) {
        Assert.isTrue(fieldValues.containsKey("id"),"未解析到id值，请检查前台传递参数是否正确");
        Object result= fieldValues.get("id");
        Long id=0L;
        if(result instanceof String){
            id= Long.parseLong((String) result);

        }else if(result instanceof Integer){
            id= ((Integer) result).longValue();
        }else if(result instanceof Long){
            id= (Long) result;
        }
        ILogService service= null;
        Class cls=enableModifyLog.serviceclass();
        service = (ILogService) SpringUtil.getBean(cls);


        return  service.selectById(id);
    }


}
