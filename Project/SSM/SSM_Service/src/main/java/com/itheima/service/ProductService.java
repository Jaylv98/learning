package com.itheima.service;

import com.itheima.domain.Product;
import java.util.List;

public interface ProductService {

    //查询所有用户信息
    public List<Product> findAll(Integer size,Integer page) throws Exception;

    //产品信息保存
    void save(Product product) throws Exception;
}
