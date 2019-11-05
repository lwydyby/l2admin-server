package com.example.cli.log.Interceptor;


import com.example.cli.entity.User;
import com.example.cli.log.DataName;
import com.example.cli.log.EnableModifyLog;
import com.example.cli.log.ModifyName;
import com.example.cli.log.entity.OperateLog;
import com.example.cli.log.parser.ContentParser;
import com.example.cli.log.repository.OperateLogRepository;
import com.example.cli.utils.ClientUtil;
import com.example.cli.utils.ReflectionUtils;
import com.example.cli.utils.RequestUserHolder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 拦截@EnableGameleyLog注解的方法
 * 将具体修改存储到数据库中
 * Created by wwmxd on 2018/03/02.
 */
@Aspect
@Component
public class ModifyAspect {

    private final static Logger logger = LoggerFactory.getLogger(ModifyAspect.class);

    private OperateLog operateLog=new OperateLog();

    private Object oldObject;

    private Object newObject;

    private Map<String,Object> feildValues;

    private Map<String ,Object> oldMap;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OperateLogRepository operateLogRepository;

    @Before("@annotation(EnableModifyLog)")
    public void doBefore(JoinPoint joinPoint, EnableModifyLog EnableModifyLog){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object info=joinPoint.getArgs()[0];
        String[] feilds=EnableModifyLog.feildName();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        User user= RequestUserHolder.getUser();
        operateLog.setUsername(user.getTrueName());
        operateLog.setModifyIp(ClientUtil.getClientIp(request));
        operateLog.setModifyDate(sdf.format(new Date()));
        String handelName=EnableModifyLog.handleName();
        if("".equals(handelName)){
            operateLog.setModifyObject(request.getRequestURL().toString());
        }else {
            operateLog.setModifyObject(handelName);
        }
        operateLog.setModifyName(EnableModifyLog.name());
        operateLog.setModifyContent("");
        if(ModifyName.UPDATE.equals(EnableModifyLog.name())){
            for(String feild:feilds){
                feildValues=new HashMap<>();
                Object result= ReflectionUtils.getFieldValue(info,feild);
                feildValues.put(feild,result);
            }
            try {
                ContentParser contentParser= (ContentParser) EnableModifyLog.parseclass().newInstance();
                oldObject=contentParser.getResult(feildValues,EnableModifyLog);
                oldMap= (Map<String, Object>) objectToMap(oldObject);
            } catch (Exception e) {
                logger.error("service加载失败:",e);
            }
        }else{
            if(ModifyName.UPDATE.equals(EnableModifyLog.name())){
                logger.error("id查询失败，无法记录日志");
            }
        }

    }

    @AfterReturning(pointcut = "@annotation(enableModifyLog)",returning = "object")
    public void doAfterReturing(Object object, EnableModifyLog enableModifyLog){
        if(ModifyName.UPDATE.equals(enableModifyLog.name())){
            ContentParser contentParser= null;
            try {
                contentParser = (ContentParser) enableModifyLog.parseclass().newInstance();
                newObject=contentParser.getResult(feildValues,enableModifyLog);
            } catch (Exception e) {
                logger.error("service加载失败:",e);
            }
            try {
                Map<String ,Object> newMap= (Map<String, Object>) objectToMap(newObject);
                StringBuilder str=new StringBuilder();
                oldMap.forEach((k,v)->{
                    Object newResult=newMap.get(k);
                    if(v!=null&&!v.equals(newResult)){
                        Field field=ReflectionUtils.getAccessibleField(newObject,k);
                        DataName dataName=field.getAnnotation(DataName.class);
                        if(dataName!=null){
                            str.append("【").append(dataName.name()).append("】从【")
                                    .append(v).append("】改为了【").append(newResult).append("】;\n");
                        }else {
                            str.append("【").append(field.getName()).append("】从【")
                                    .append(v).append("】改为了【").append(newResult).append("】;\n");
                        }
                    }

                });
                operateLog.setModifyContent(str.toString());

            } catch (Exception e) {
                logger.error("比较异常",e);
            }
        }
        operateLogRepository.save(operateLog);


    }

    private  Map<?, ?> objectToMap (Object obj) {
        if (obj == null) {
            return null;
        }
        Map<?, ?> mappedObject = objectMapper.convertValue(obj, Map.class);

        return mappedObject;
    }



}
