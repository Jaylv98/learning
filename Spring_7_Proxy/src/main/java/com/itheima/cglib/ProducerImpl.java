package com.itheima.cglib;


import com.itheima.proxy.Producer;

public class ProducerImpl  {

    public void saleProduct(Float money) {
        System.out.println("销售产品，并拿到钱："+money);

    }


    public void after(Float money) {
        System.out.println("提供售后服务，并拿到钱"+money);
    }
}
