package com.itheima.service.impl;


import com.itheima.service.IAccountService;

import java.util.Date;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl2 implements IAccountService {

    //如果是经常变化的数据，并不适用于注入的方式
    private String name;
    private Integer age;
    private Date birthday;


    //set方式注入只需要set方法  不需要get方法
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void  saveAccount(){
        System.out.println("service中的save方法已执行，name:"+name+";age:"+age+";birthday:"+birthday);


    }


}
