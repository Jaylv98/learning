package com.itheima.factory;


import com.itheima.domain.Account;
import com.itheima.service.AccountService;
import com.itheima.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

//用于创建Service的代理对象的工厂
public class BeanFactory {

    private AccountService accountService;
    private  TransactionManager txManager;

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }


    public final void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    //获取service的代理对象
    public AccountService getAccountService(){
        AccountService accountService1=(AccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object rtValue=null;
                        try{
                            //开启事务
                            txManager.beginTransaction();
                            //执行操作
                            rtValue = method.invoke(accountService, args);
                            //提交事务
                            txManager.commit();
                            //返回结果
                            return rtValue;
                        }catch (Exception e){
                            //回滚事务
                            txManager.rollback();
                            throw new RuntimeException(e);
                        }finally {
                            //释放连接
                            txManager.release();
                        }
                    }
                });
        return accountService1;
    }
}
