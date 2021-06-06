package com.itheima.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//模拟消费者
public class Client {


    public static void main(String[] args) {


        final Producer producer=new ProducerImpl();

        /*
         *       动态代理：
         *           特点：字节码随用随创建，随用随加载
         *           作用：不修改源码的基础上对方法增强
         *           分类：
         *               基于接口的动态代理
         *               基于子类的动态代理
         *           基于接口的动态代理：
         *               涉及的类：Proxy
         *               提供者：JDK
         *           如何创建代理对象：
         *               使用Proxy类中的newProxyInstance
         *           创建代理对象的要求：
         *               被代理类最少实现一个接口，如果没有，则不能使用
         *           newProxyInstance方法的参数：
         *               ClassLoader：类加载器
         *                   用于加载代理对象字节码，写的是被代理对象的类加载器，固定写法
         *               Class[]：字节码数组
         *                   用于让代理对象和被代理对象有相同的方法。固定写法
         *               InvocationHandler:用于增强的代码
         *                    他是让我们如何写代码，一般都是写一个该接口的实现类，通常情况下都是匿名内部类，但不是必须的
         *                    此接口的实现类都是谁用谁写
         */
        Producer proxyProducer = (Producer) Proxy.newProxyInstance(producer.getClass().getClassLoader(),
                producer.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    /*
                     *   作用：执行被代理对象的任何接口方法都会经过该方法
                     *   参数：
                     *       Object proxy：代理对象的引用
                     *       Method method：当前执行的方法
                     *       Object[] args：当前执行方法所需的参数
                     *   返回值：
                     *       和被代理对象方法有相同的返回值
                     * */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object returnValue=null;
                        //提供增强的代码
                        //获取方法执行的参数
                        Float money=(Float) args[0];
                        //判断当前方法是否为销售
                        if("saleProduct".equals(method.getName())){
                            returnValue=method.invoke(producer,money*0.8f);
                        }
                        return returnValue;
                    }
                });


        proxyProducer.saleProduct(10000f);

    }

}
