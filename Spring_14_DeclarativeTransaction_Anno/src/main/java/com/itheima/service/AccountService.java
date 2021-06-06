package com.itheima.service;


import com.itheima.domain.Account;

//账户业务层接口
public interface AccountService {

    //根据Id查找账户
    Account findAccountById(Integer accountId);

    //转账
    void transfer(String sourceName,String targetName,Float money);
}
