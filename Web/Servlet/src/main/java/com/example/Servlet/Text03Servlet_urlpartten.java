package com.example.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//@WebServlet({"/d3","/dd3","/ddd3"})   //任意一个都可
//@WebServlet("/user/demo3")            //两个都得写
//@WebServlet("/user/*")                //*代表通配符   任意内容都可
//@WebServlet("/*")
@WebServlet("*.do")
public class Text03Servlet_urlpartten extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo3运行");
    }
}
