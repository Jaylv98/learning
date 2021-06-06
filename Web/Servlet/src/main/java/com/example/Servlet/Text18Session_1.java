package com.example.Servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/*

服务器端会话技术：Session
        概念：服务器端会话技术，在一次会话的多次请求间共享数据，将数据保存在服务器端的对象中。
        快速入门：
            1.获取HttpSession对象：
                request.getSession();
            2.使用HttpSession对象：
                void setAttribute(String name)
                Object setAttribute(String name,Object value)
                void removeAttribute(String name)

*/

@WebServlet(name = "Text18Session_1", value = "/Text18Session_1")
public class Text18Session_1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute("msg","HelloSession");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
