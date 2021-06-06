package com.itheima.mybatis.io;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

//使用类加载器读取文件
public class Resources {


    //根据传入的参数获取一个字节输入流
    public static InputStream getResourceAsStream(String filePath) {

        /*
         *   Resources.class     获取当前类的字节码
         *   Resources.class.getClassLoader()    获取类加载器
         *   Resources.class.getClassLoader().getResourceAsStream(filePath)      根据类加载器读取配置文件
         *
         * */
        return Resources.class.getResourceAsStream("/xml/SqlMapConfig.xml");



    }
}
