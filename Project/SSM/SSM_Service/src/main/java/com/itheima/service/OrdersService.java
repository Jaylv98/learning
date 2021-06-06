package com.itheima.service;

import com.itheima.domain.Orders;

import java.util.List;

public interface OrdersService {

    //查询所有订单
    List<Orders> findAll(int size,int page)throws Exception;

    //根据id查询
    Orders findById(Integer id);
}
