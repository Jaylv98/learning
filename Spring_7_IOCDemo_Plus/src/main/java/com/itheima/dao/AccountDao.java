package com.itheima.dao;


import com.itheima.domain.Account;

import java.util.List;

//账户持久层接口
public interface AccountDao {

    //查询所有
    List<Account> findAllAccount();

    //查询一个
    Account findAccountById(Integer accountId);

    //保存
    void saveAccount(Account account);

    //更新
    void updateAccount(Account account);

    //删除
    void deleteAccount(Integer accountId);

    /*
    *   根据名称查询账户
    *       如果有唯一一个结果就返回，如果没有就返回null，如果结果集超过一个就抛异常
    * */
    Account findAccountByName(String accountName);
}
