package com.example.cli.utils;

import java.util.UUID;

/**
 * @author liwei
 * @title: UUIDUtils
 * @projectName work
 * @description: TODO
 * @date 2019-08-08 16:29
 */
public class UUIDUtils {
    public static String getUUID(){
        String uuid= UUID.randomUUID().toString();
        return uuid.replaceAll("-","");
    }
}
