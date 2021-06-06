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

   /* 获取Spring的IOC核心容器，并根据id获取对象

        ApplicationContext的三个常用实现类
            ClassPathXmlApplicationContext:可以加载类路径下的配置文件，要求配置文件必须在类路径下，否则无法加载
            FileSystemXmlApplicationContext:可以加载磁盘任意路径下的文件
            AnnotationConfigApplicationContext:用于读取注解创建容器



        核心容器的两个接口引发的问题
            ApplicationContext:     适用于单例对象
                    在创建核心容器时，创建对象采用的是立即加载的方式，即一读取完配置文件马上创建配置文件中配置的对象

            BeanFactory:        适用于多例对象
                    在构建核心容器时，创建对象采用的是延迟加载的方式，即何时根据Id获取对象，何时创建对象

*/
    public static void main(String[] args) {

        /*========================ApplicationContext=========================================*/
/*
        //获取核心容器对象
        ApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");
        //ApplicationContext ac=new FileSystemXmlApplicationContext("C:\\Users\\Administrator\\IdeaProjects\\Spring_1_Introduction\\src\\main\\resources\\bean.xml");

        //根据id获取bean对象
        IAccountService as=(IAccountService)ac.getBean("accountService");
        IAccountDao ad=(IAccountDao)ac.getBean("accountDao");

        System.out.println(as);
        System.out.println(ad);
        as.saveAccount();
*/


        /*========================BeanFactory=========================================*/
        Resource r=new ClassPathResource("bean.xml");
        BeanFactory factory=new XmlBeanFactory(r);

        IAccountService as = (IAccountService) factory.getBean("accountService");
        System.out.println(as);
    }

}
