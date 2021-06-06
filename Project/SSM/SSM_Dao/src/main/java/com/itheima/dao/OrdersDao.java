package com.itheima.dao;

import com.itheima.domain.Member;
import com.itheima.domain.Orders;
import com.itheima.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrdersDao {

    //查询所有订单信息
    @Select("select * from orders")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(property ="orderNum" ,column ="orderNum" ),
            @Result(property ="orderTime" ,column ="orderTime" ),
            @Result(property ="peopleCount" ,column ="peopleCount" ),
            @Result(property ="orderDesc" ,column ="orderDesc" ),
            @Result(property ="payType" ,column ="payType" ),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.itheima.dao.ProductDao.findByNum")),

    })
    public List<Orders> findAll() throws Exception;

    //根据编号查询
    @Select("select * from orders where id =#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(property ="orderNum" ,column ="orderNum" ),
            @Result(property ="orderTime" ,column ="orderTime" ),
            @Result(property ="peopleCount" ,column ="peopleCount" ),
            @Result(property ="orderDesc" ,column ="orderDesc" ),
            @Result(property ="payType" ,column ="payType" ),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.itheima.dao.ProductDao.findByNum")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select = "com.itheima.dao.MemberDao.findById")),
            @Result(property = "travellers",column = "orderNum",javaType = java.util.List.class,many = @Many(select = "com.itheima.dao.TravellerDao.findByOrderId"))
    })
    public Orders findById(Integer id);
}
