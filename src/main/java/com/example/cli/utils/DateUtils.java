package com.example.cli.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liwei
 * @title: DateUtils
 * @projectName luwu
 * @description: TODO
 * @date 2019-11-05 09:43
 */
public class DateUtils {

    public static String getNowDate(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    public static void main(String[] args) {
        System.out.println(MD5Utils.stringToMD5("123456"));
    }
}
