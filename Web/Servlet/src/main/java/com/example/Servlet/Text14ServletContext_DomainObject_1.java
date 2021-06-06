package com.example.Servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
/*

    ServletContext对象功能：
                域对象：共享数据
                        setAttribute(String name,Object value)：设置数据
                        getAttribute(String name)：获取数据
                        removeAttribute(String name)：移除数据

                        ServletContext对象范围：所有用户所有请求的数据
*/

@WebServlet(name = "Text14ServletContext_DomainObject", value = "/Text14ServletContext_DomainObject")
public class Text14ServletContext_DomainObject_1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取ServletContext对象
        ServletContext context = this.getServletContext();

        //设置数据
        context.setAttribute("msg","hehe");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request,response);
    }
}
