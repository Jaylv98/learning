package cn.itcast.utils;


import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


//字符串转换为日期
public class StringToDateConverter implements Converter<String, Date> {


    @Override
    public Date convert(String source) {
        //判断参数是否为空
        if(source==null){
            throw new RuntimeException("参数为空");
        }
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");

        try {
            //把字符串转换日期
            return df.parse(source);
        } catch (Exception e) {
            throw new RuntimeException("类型转换出错");
        }
    }
}
