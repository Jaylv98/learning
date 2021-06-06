package com.itheima.utils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

//连接工具类，用于从数据源中获取一个连接，并且实现和线程的绑定
public class ConnectionUtils {

    private ThreadLocal<Connection> tl=new ThreadLocal<Connection>();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //获取当前线程连接
    public Connection getThreadConnection(){
            try {
                //从ThreadLocal上获取
                Connection conn= tl.get();
                //判断当前线程上是否有连接
                if (conn==null) {
                    //从数据源中获取一个连接，且存入ThreadLocal中
                    conn = dataSource.getConnection();
                    tl.set(conn);
                }
                //返回当前线程上的连接
                return conn;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }

    //将连接与线程解绑
    public void removeConnection(){
        tl.remove();
    }
}
