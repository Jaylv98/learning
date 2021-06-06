package com.example.Servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Text16Cookie_2", value = "/Text16Cookie_2")
public class Text16Cookie_2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取Cookie
        Cookie[] cookies = request.getCookies();
        //获取数据，遍历Cookie
        if(cookies!=null){
            for (Cookie cs:cookies) {
                String name = cs.getName();
                String value = cs.getValue();
                System.out.println(name+":"+value);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
