package com.itheima.controller;


import com.github.pagehelper.PageInfo;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //保存产品信息
    @RequestMapping("/save")
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:findAll";

    }

    //查询所有产品信息
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                @RequestParam(name ="size",required = true,defaultValue = "5") Integer size)throws Exception{
        ModelAndView mv=new ModelAndView();
        List<Product> ps = productService.findAll(page,size);
        //PageHelper提供的一个分页bean
        PageInfo pageInfo=new PageInfo(ps);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product-list");

        return mv;
    }
}
