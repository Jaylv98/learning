package com.example.Servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Enumeration;

/*

    获取请求头数据
            方法：
                 (常用)String getHeader(String name)：通过请求头的名称获取请求头的值
                 Enumeration<String> getHeaderNames()：获取所有的请求头名称

*/

@WebServlet( value = "/Text05RequestHeader")
public class Text05RequestHeader extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*
        //获取请求头数据
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            //根据名称获取请求头的值
            String value = request.getHeader(name);
            System.out.println(name+"--"+value);
        }*/


       /* //获取请求头数据：user-agent
        String agent = request.getHeader("user-agent");
        //判断浏览器版本
        if(agent.contains("Chrome")){

            System.out.println("谷歌");
        }else if(agent.contains("FireFox")){

            System.out.println("火狐");
        }*/


        //获取请求头数据：referer
        String referer = request.getHeader("referer");
        System.out.println(referer);

        //防盗链
        if(referer != null){
            if(referer.contains("/request")){
                //正常访问
                // System.out.println("正常访问");
                //显示中文
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("正常访问");
            }else{
                //盗链
                //System.out.println("盗链");
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("存在盗链，不允许访问");
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
