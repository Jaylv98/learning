package com.itheima.ui;


import com.itheima.dao.IAccountDao;
import com.itheima.service.IAccountService;
import com.itheima.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 模拟一个表现层，用于调用业务层
 */



public class Client {


    public static void main(String[] args) {


        //获取核心容器对象
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //根据id获取bean对象
        IAccountService as = (IAccountService) ac.getBean("accountService");

      /*  IAccountDao ad=(IAccountDao) ac.getBean("accountDao") ;
        System.out.println(ad);
        ad.saveAccount();
        System.out.println("=======================");

        System.out.println(as);*/
        as.saveAccount();

        ac.close();
    }

}
