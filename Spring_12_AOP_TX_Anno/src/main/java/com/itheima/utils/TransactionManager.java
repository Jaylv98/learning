package com.itheima.utils;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

//与事务管理相关的工具类，包含开启事务，提交事务，回滚事务，释放连接
@Component("txManager")
@Aspect
public class TransactionManager {

    @Autowired
    private ConnectionUtils connectionUtils;

    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    private void pt1(){}


    //开启事务
    //@Before("pt1()")
    public void beginTransaction(){
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    //提交事务
    //@AfterReturning("pt1()")
    public void commit(){
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    //回滚事务
   // @AfterThrowing("pt1()")
    public void rollback(){
        try {
            connectionUtils.getThreadConnection().rollback();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    //释放连接
    //@After("pt1()")
    public void release(){
        try {
            connectionUtils.getThreadConnection().close();//还回连接池中
            connectionUtils.removeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }




    //环绕通知
    @Around("pt1()")
    public Object aroundAdvice(ProceedingJoinPoint pjp){
        Object rtValue=null;
            try{
                //获取参数
                Object[] args = pjp.getArgs();
                //开启事务
                this.beginTransaction();
                //执行方法
                rtValue=pjp.proceed(args);
                //提交事务
                this.commit();
                return rtValue;
            }catch (Throwable r){
                this.rollback();
                throw new RuntimeException(r);
            }finally {
                this.release();
            }
    }
}
