package com.example.cli.valid.utils;

import java.util.Arrays;
import java.util.List;

/**
 * @author liwei
 * @title: GroupUtils
 * @projectName sdkproxy
 * @description: TODO
 * @date 2020-01-07 09:47
 */
public class GroupUtils {

    public static boolean isInGroup(Class[] group,Class[] validGroup){
        //校验分组
        List<Class> groupList= Arrays.asList(group);
        groupList.retainAll(Arrays.asList(validGroup));
        if(groupList.size()==0){
            //不在校验范围内，跳出
            return false;
        }
        return true;

    }
}
