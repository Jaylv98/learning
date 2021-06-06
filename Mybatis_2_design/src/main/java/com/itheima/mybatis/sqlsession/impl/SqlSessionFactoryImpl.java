package com.itheima.mybatis.sqlsession.impl;

import com.itheima.mybatis.cfg.Configuration;
import com.itheima.mybatis.sqlsession.SqlSession;
import com.itheima.mybatis.sqlsession.SqlSessionFactory;

//SqlSessionFactory接口的实现类
public class SqlSessionFactoryImpl implements SqlSessionFactory {

    private Configuration cfg;

    public SqlSessionFactoryImpl(Configuration cfg){
            this.cfg=cfg;
    }

    @Override
    //用于创建新的操作数据库对象
    public SqlSession openSession() {

        return new SqlSessionImpl(cfg);
    }
}
