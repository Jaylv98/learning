package com.itheima.ui;


import com.itheima.service.IAccountService;
import com.itheima.service.impl.AccountServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


/**
 * 模拟一个表现层，用于调用业务层
 */



public class Client {

    public static void main(String[] args) {

        //获取核心容器对象
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");

        //根据id获取bean对象
        IAccountService as = (IAccountService) ac.getBean("accountService");

       as.saveAccount();

       ac.close();


    }


}
