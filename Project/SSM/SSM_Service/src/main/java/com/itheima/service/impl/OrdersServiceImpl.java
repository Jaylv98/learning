package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.OrdersDao;
import com.itheima.domain.Orders;
import com.itheima.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    //查询所有订单信息
    @Override
    public List<Orders> findAll(int page,int size) throws Exception {
        /*
                 PageHelper是mybatis的一个分页展示插件
                    pageNum:页码值；
                    pageSize:每页显示的条数
        */
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    //根据编号查询
    @Override
    public Orders findById(Integer id) {
        return ordersDao.findById(id);
    }
}
