package com.itheima.proxy;

//创建一个生产者类
public interface Producer {

    //销售
    void saleProduct(Float money);

    //售后
    void after(Float money);
}
