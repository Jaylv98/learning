package com.itheima.dao;

import com.itheima.domain.Account;

import java.util.List;

public interface AccountDao {

    //查询所有账户
    List<Account> findAll();


    //根据用户id查询用户信息
    List<Account> findAccountByUid(Integer uid);
}
