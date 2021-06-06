package com.itheima.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

//完成日期类型与字符串类型相互转换的功能
public class DateUtils {

    //日期转换成字符串
    //pattern:需要转换成字符串的格式
    public static String dateToString(Date date,String pattern){
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        String format = sdf.format(date);
        return format;
    }


    //字符串转换成日期
    public static Date stringToDate(String date,String pattern) throws Exception{
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        Date parse = sdf.parse(date);
        return parse;
    }
}

