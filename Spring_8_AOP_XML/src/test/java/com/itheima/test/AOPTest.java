package com.itheima.test;

import com.itheima.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//测试aop配置
public class AOPTest {
    public static void main(String[] args) {
        // 获取容器
        ApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");
        //获取对象
         AccountService as=(AccountService) ac.getBean("accountService");
         //执行方法
        as.saveAccount();

    }
}
