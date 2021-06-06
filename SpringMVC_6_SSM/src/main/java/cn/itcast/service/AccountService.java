package cn.itcast.service;

import cn.itcast.domain.Account;

import java.util.List;

public interface AccountService {


    //查询所有用户信息
    public List<Account> findAll();

    //保存账户
    public void saveAccount(Account account);
}
