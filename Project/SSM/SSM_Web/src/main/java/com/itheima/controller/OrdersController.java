package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Orders;
import com.itheima.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    //查询全部订单----未分页
   /* @RequestMapping("findAll")
    public ModelAndView findAll() throws Exception{
        ModelAndView mv=new ModelAndView();
        List<Orders> orders = ordersService.findAll();
        mv.addObject("ordersList",orders);
        mv.setViewName("orders-list");
        return mv;
    }*/


    //查询全部订单---分页
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                @RequestParam(name ="size",required = true,defaultValue = "5") Integer size)throws Exception{

        ModelAndView mv=new ModelAndView();
        List<Orders> orders = ordersService.findAll(page, size);
        //PageHelper提供的一个分页bean
        PageInfo pageInfo=new PageInfo(orders);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-pages-list");
        return mv;
    }

    //根据编号查询
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true) Integer id){
        ModelAndView mv=new ModelAndView();
        Orders orders=ordersService.findById(id);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");

        return mv;
    }
}
