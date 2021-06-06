package com.itheima.test;

import com.itheima.domain.Account;
import com.itheima.service.AccountService;
import config.JdbcConfiguration;
import config.SpringConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;



/*
*       使用junit单元测试，测试配置
* */
public class AccountTest {

    //获取容器
    ApplicationContext ac=new AnnotationConfigApplicationContext(SpringConfiguration.class);
    //得到业务层对象
    AccountService as = ac.getBean("accountService",AccountService.class);

    @Test
    public void testFindAll() {
        //执行方法
        List<Account> accounts= as.findAllAccount();
        for (Account account :accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne() {
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave() {
        Account account=new Account();
        account.setName("张三");
        account.setMoney(6000f);
        as.saveAccount(account);
    }

    @Test
    public void testUpdate() {
        Account account = as.findAccountById(4);
        account.setName("李四");
        as.updateAccount(account);
    }

    @Test
    public void testDelete() {
        as.deleteAccount(4);
    }
}
