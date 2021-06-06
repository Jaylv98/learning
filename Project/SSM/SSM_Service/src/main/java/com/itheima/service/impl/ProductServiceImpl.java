package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    //查询所有用户信息
    @Override
    public List<Product> findAll(Integer page,Integer size) throws Exception {
         /*
                 PageHelper是mybatis的一个分页展示插件
                    pageNum:页码值；
                    pageSize:每页显示的条数
        */
        PageHelper.startPage(page,size);
        return productDao.findAll();
    }

    //保存用户信息
    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }
}
