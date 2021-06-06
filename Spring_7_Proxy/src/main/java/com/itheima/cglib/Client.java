package com.itheima.cglib;

import com.itheima.proxy.Producer;
import com.itheima.proxy.ProducerImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//模拟消费者
public class Client {


   /* public static void main(String[] args) {


        final Producer producer=new ProducerImpl();

        *//*
         *       动态代理：
         *           特点：字节码随用随创建，随用随加载
         *           作用：不修改源码的基础上对方法增强
         *           分类：
         *               基于接口的动态代理
         *               基于子类的动态代理
         *           基于子类的动态代理：
         *               涉及的类：Enhancer
         *               提供者：第三方cglib库
         *           如何创建代理对象：
         *               使用Enhancer类中的creat方法
         *           创建代理对象的要求：
         *               被代理类不能是最终类
         *           create()方法的参数：
         *              Class:字节码
         *                  用于指定被代理对象的字节码
         *              Callback：用于增强代码
         *                  一般写的都是子接口的实现类：MethodInterceptor
         *
         *//*
        Producer cglibProducer=(Producer) Enhancer.create(producer.getClass(), new MethodInterceptor() {
            @Override
            //执行被代理对象的方法都会经过该方法
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Object returnValue=null;
                //提供增强的代码
                //获取方法执行的参数
                Float money = (Float) args[0];
                //判断当前方法是否为销售
                if("saleProduct".equals(method.getName())){
                    returnValue=method.invoke(producer,money*0.5f);
                }
                return returnValue;
            }
        });

        cglibProducer.saleProduct(10000f);
    }*/

}
