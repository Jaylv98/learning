package com.itheima.dao;


import com.itheima.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductDao {

    //查询所有用户信息
    @Select("select * from product")
    public List<Product> findAll() throws Exception;

    //保存用户信息
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) " +
            " values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product) throws Exception;

    //根据id查询产品
    @Select("select * from product where productNum=#{productNum}")
    public Product findByNum(String productNum)throws Exception;
}
