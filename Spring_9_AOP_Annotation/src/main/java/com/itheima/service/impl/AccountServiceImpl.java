package com.itheima.service.impl;


import com.itheima.service.AccountService;
import org.springframework.stereotype.Service;

//账户的业务层实现类
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Override
    public void saveAccount() {
        System.out.println("保存操作已执行");
       // int i=1/0;
    }

    @Override
    public void updateAccount(int i) {
        System.out.println("更新操作已执行"+i);
    }

    @Override
    public int deleteAccount() {
        System.out.println("删除操作已执行");
        return 0;
    }
}
