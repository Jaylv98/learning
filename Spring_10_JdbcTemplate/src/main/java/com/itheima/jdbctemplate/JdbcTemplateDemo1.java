package com.itheima.jdbctemplate;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

//JdbcTemplate的基本用法
public class JdbcTemplateDemo1 {
    public static void main(String[] args) {
        //准备数据源：Spring内置数据源
        DriverManagerDataSource ds=new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/spring_1");
        ds.setUsername("root");
        ds.setPassword("1234");

        //创建对象
        JdbcTemplate jt=new JdbcTemplate();
        //给jt设置数据源
        jt.setDataSource(ds);
        //执行操作
        jt.execute("insert into account(name,money) values ('张三',1000)");
    }
}
