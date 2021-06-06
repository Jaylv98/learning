package com.itheima.jdbctemplate;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

//JdbcTemplate在spring的ioc中使用
public class JdbcTemplateDemo2 {
    public static void main(String[] args) {
       //获取容器
        ApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");
        //获取对象
        JdbcTemplate jt = ac.getBean("jdbcTemplate", JdbcTemplate.class);
        //执行操作
        jt.execute("insert into account(name,money) values ('王五',1000)");
    }
}
