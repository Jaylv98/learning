package com.example.Servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;



/*
*       Request对象获取请求行数据
*               方法：
							获取请求方式：GET
								String getMethod()
							(常用)获取虚拟目录：/day14
								String getContextPath()
							获取Servlet路径：/demo1
								String getServletPath()
							获取get方式请求参数：name=zhangsan
								String getQueryString()
							(常用)获取请求URI：/day14/demo1
								String getRequestURI()：	  /day14/demo1
								StringBuffer getRequestURL()：http://localhost/day14/demo1
							获取协议及版本：HTTP/1.1
								String getProtocol()
							获取客户机的IP地址：
								String getRemoteAddr()
*
*
*
*
* */
@WebServlet( value = "/Text04RequestLine")
public class Text04RequestLine extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //String getMethod():获取请求方式
        String method = request.getMethod();
        System.out.println(method);

        //String getContextPath()：获取虚拟目录
        String contextPath = request.getContextPath();
        System.out.println(contextPath);

        //String getServletPath()：获取Servlet路径
        String servletPath = request.getServletPath();
        System.out.println(servletPath);

        //String getQueryString():获取get方式请求参数
        String queryString = request.getQueryString();
        System.out.println(queryString);

        //String getRequestURI()：获取请求URI
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        //StringBuffer getRequestURL()：获取请求URL
        StringBuffer requestURL = request.getRequestURL();
        System.out.println(requestURL);

        //String getProtocol():获取协议及版本号
        String protocol = request.getProtocol();
        System.out.println(protocol);

        //String getRemoteAddr():获取客户机的IP地址
        String remoteAddr = request.getRemoteAddr();
        System.out.println(remoteAddr);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
}
