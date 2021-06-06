package com.itheima.mybatis.sqlsession;

import com.itheima.mybatis.cfg.Configuration;
import com.itheima.mybatis.sqlsession.impl.SqlSessionFactoryImpl;
import com.itheima.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

//用于创建SqlSessionFactory对象
public class SqlSessionFactoryBuilder {

    //根据参数的字节输入流来构建一个SqlSesionFactory工厂
    public SqlSessionFactory build(InputStream in) throws Exception{

        Configuration cfg= XMLConfigBuilder.loadConfiguration(in);

        return new SqlSessionFactoryImpl(cfg);
    }
}

