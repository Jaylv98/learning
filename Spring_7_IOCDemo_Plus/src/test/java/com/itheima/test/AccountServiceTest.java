package com.itheima.test;

import com.itheima.domain.Account;
import com.itheima.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 使用Junit单元测试：测试我们的配置
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountServiceTest {

    @Autowired
    @Qualifier("proxyAccountService")
    private AccountService as;

    @Test
    public void testFindAll(){
        List<Account> accounts=as.findAllAccount();
        for (Account account :accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void testTransfer(){
        as.transfer("aaa","bbb",100f);
    }
}
