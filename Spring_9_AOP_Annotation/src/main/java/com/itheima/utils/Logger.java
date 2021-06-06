package com.itheima.utils;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//用于记录日志的工具类，提供公共代码
@Component("logger")
@Aspect//表示当前类是一个切面类
public class Logger {

    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    public void pt1(){}

    //前置通知
    @Before("pt1()")
    public void beforePrintLog(){
        System.out.println("前置通知：Logger类中的beforePrintLog已开始记入日志");
    }


    //后置通知
    @AfterReturning("pt1()")
    public void afterReturningPrintLog(){
        System.out.println("后置通知：Logger类中的afterReturningPrintLog已开始记入日志");
    }

    //异常通知
    @AfterThrowing("pt1()")
    public void afterThrowingPrintLog(){
        System.out.println("异常通知：Logger类中的afterThrowingPrintLog已开始记入日志");
    }

    //最终通知
    @After("pt1()")
    public void afterPrintLog(){
        System.out.println("最终通知：Logger类中的afterPrintLog已开始记入日志");
    }


    //环绕通知
    /*
    *   问题：
    *       当配置环绕通知之后，切入点方法未执行，通知方法执行
    *   分析：
    *       通过对比动态代理中的环绕通知代码，发现动态代理的环绕通知有明确的业务层切入点方法调用，而此代码中没有
    *   解决：
    *       Spring框架中提供了一个接口：ProceedingJoinPoint，该接口中的proceed()方法就相当于明确调用切入点方法。
    *       该接口可以作为环绕通知的接口参数，在程序执行时，Spring框架会提供该接口的实现类
    *
    *   Spring中的环绕通知：
    *       是Spring框架提供的一种可以在代码中手动控制增强方法何时执行的方式
    * */
    //@Around("pt1()")
    public Object aroundPrintLog(ProceedingJoinPoint pjp){
        Object rtvalue=null;
        try{
            Object[] args = pjp.getArgs();//得到方法运行所需的参数
            System.out.println("环绕通知：Logger类中的beforePrintLog已开始记入日志     ----前置通知");
            rtvalue=pjp.proceed(args);//明确调用切入点方法
            System.out.println("环绕通知：Logger类中的afterReturningPrintLog已开始记入日志         ----后置通知");
            return rtvalue;
        }catch (Throwable t){
            System.out.println("环绕通知：Logger类中的afterThrowingPrintLog已开始记入日志      ----异常通知");
            throw new RuntimeException(t);
        }finally {
            System.out.println("环绕通知：Logger类中的afterPrintLog已开始记入日志      ----最终通知");
        }
    }
}
