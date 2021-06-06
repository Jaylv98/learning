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
        //构造函数方式注入
        IAccountService as1 = (IAccountService) ac.getBean("accountService");
        System.out.println("构造函数注入:");
        as1.saveAccount();


        //set方法注入
        IAccountService as2 = (IAccountService) ac.getBean("accountService2");
        System.out.println("set方法注入:");
        as2.saveAccount();

        // //set方法注入
        IAccountService as3 = (IAccountService) ac.getBean("accountService3");
        System.out.println("set方法注入复杂类型:");
        as3.saveAccount();
    }


}
