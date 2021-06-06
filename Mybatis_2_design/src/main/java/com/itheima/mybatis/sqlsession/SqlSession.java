package com.itheima.mybatis.sqlsession;


//自定义mybatis中和数据库交互的核心类，可以创建Dao代理的核心对象
public interface SqlSession {
    //根据参数创建代理对象，参数是Dao的接口字节码
    <T> T getMapper(Class<T> daoInterfaceClass);

    //释放资源
    void close();
}
