package com.example.Servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/*
*           Cookie快速入门：
*                   步骤：
*                       创建Cookie对象，绑定数据
*                           new Cookie(String name,String value)
*                       发送Cookie对象
*                           response.addCookie(Cookie cookie)
*                       获取Cookie，拿到数据
*                           Cookie[]  request.getCookie()
*
* */
@WebServlet(name = "Text16Cookie_1", value = "/Text16Cookie_1")
public class Text16Cookie_1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //创建Cookie对象
        Cookie c=new Cookie("msg","HelloCookie");
        //发送Cookie
        response.addCookie(c);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
