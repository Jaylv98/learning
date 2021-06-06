package com.itheima.mybatis.sqlsession.impl;

import com.itheima.mybatis.cfg.Configuration;
import com.itheima.mybatis.sqlsession.SqlSession;
import com.itheima.mybatis.sqlsession.proxy.MapperProxy;
import com.itheima.mybatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

public class SqlSessionImpl implements SqlSession {

    private Configuration cfg;
    private Connection connection;
    public SqlSessionImpl(Configuration cfg){
        this.cfg=cfg;
        connection= DataSourceUtil.getConnection(cfg);
    }

    //用于创建代理对象
    @Override
    public <T> T getMapper(Class<T> daoInterfaceClass) {

       return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass},new MapperProxy(cfg.getMappers(),connection));


    }

    //释放资源
    @Override
    public void close() {
        if(connection!=null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
