package com.itheima.jdbctemplate;


import com.itheima.dao.AccountDao;
import com.itheima.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

//JdbcTemplate的CRUD操作
public class JdbcTemplateDemo3 {
    public static void main(String[] args) {
       //获取容器
        ApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");
        //获取对象
        AccountDao accountDao = ac.getBean("accountDao", AccountDao.class);
        //执行操作
        Account account=accountDao.findAccountById(1);
        System.out.println(account);

        account.setMoney(1000f);
        accountDao.updateAccount(account);
    }
}
