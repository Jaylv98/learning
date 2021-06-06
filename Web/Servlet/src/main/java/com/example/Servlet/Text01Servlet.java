package com.example.Servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;



/*
*               快速入门：
*		                1.创建JavaEE项目
*		                2.定义一个类，实现Servlet接口
*		                3.实现接口中的抽象方法
*		                4.配置Servlet
*			                    <servlet>
*       				            <servlet-name>demo1</servlet-name>
*        				            <servlet-class>com.example.Servlet.Text01Servlet</servlet-class>
*
*    			                </servlet>>
*
*    			                <servlet-mapping>
*        				            <servlet-name>demo1</servlet-name>
*        				            <url-pattern>/demo1</url-pattern>
*    			                </servlet-mapping>
*
* */
@WebServlet("/demo1")
public class Text01Servlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    //提供服务的方法
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("Hello Servlet");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
