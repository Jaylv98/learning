package com.itheima.service.impl;


import com.itheima.service.IAccountService;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {


    public AccountServiceImpl(){
        System.out.println("对象已创建");
    }

    public void  saveAccount(){
        System.out.println("service中的saveAccount方法已执行");

    }

    public void  init(){
        System.out.println("对象已初始化");

    }

    public void  destroy(){
        System.out.println("对象已销毁");

    }
}
