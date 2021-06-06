package com.itheima.dao;


import com.itheima.domain.Account;

//账户的持久层接口
public interface AccountDao {

    //根据id查询
    Account findAccountById(Integer accountId);

    //根据姓名查询
    Account findAccountByName(String accountName);

    //更新账户
    void updateAccount(Account account);
}
