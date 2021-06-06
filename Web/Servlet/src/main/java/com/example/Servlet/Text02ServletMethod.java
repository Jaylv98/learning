package com.example.Servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;


/*
*
*       Servlet方法
* */

@WebServlet("/demo2")
public class Text02ServletMethod implements Servlet {



    //  初始化方法：在Servlet被创建时执行，只会执行一次
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init方法执行");
    }


    /*
    *   获取ServletConfig对象
    *         ServletConfig：Servlet配置对象
    * */
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }


    // 提供服务方法：每次Servlet被访问时执行，执行多次
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service方法执行");
    }


    //获取Servlet的信息：版本，作者等
    @Override
    public String getServletInfo() {
        return null;
    }



    // 销毁方法：服务器正常关闭时执行，执行一次
    @Override
    public void destroy() {
        System.out.println("destroy方法执行");
    }
}
