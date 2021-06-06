package com.example.Servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


/*
*
*                   重定向特点：redirect
*                           1.地址栏发生变化
*                           2.重定向可以访问其他站点（服务器）的资源
*                           3.重定向是两次请求，不可以使用request对象共享数据
*
*                   转发特点：forward
*							1.浏览器地址栏路径无变化
*							2.只能转发到当前服务器内部资源中
*							3.转发是一次请求,可以使用request对象共享数据
*
* */


@WebServlet(name = "Text09ResponseRedirect_1", value = "/Text09ResponseRedirect_1")
public class Text09ResponseRedirect_1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Text09ResponseRedirect_1访问成功");

        //访问/Text09ResponseRedirect_1，会自动跳转到/Text09ResponseRedirect_2
      /*  //设置状态码 302
        response.setStatus(302);
        //设置响应头location
        response.setHeader("location","/Text09ResponseRedirect_2");*/

        //简化代码
        //response.sendRedirect("/Text09ResponseRedirect_2");

        response.sendRedirect("https://www.baidu.com/");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request,response);
    }
}
