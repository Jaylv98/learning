package com.example.Servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/*

    ServletContext对象：
            概念：代表整个web应用，可以和程序的容器来通信
            获取：
                通过request对象获取：
                        request.getServletContext();
                通过HttpServlet获取：
                        this.getServletContext();
            功能：
				获取MIME类型：
				域对象：共享数据：
				获取文件的真实路径

*/
@WebServlet(name = "Text12ServletContext", value = "/Text12ServletContext")
public class Text12ServletContext extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //通过request对象获取
        ServletContext context1 = request.getServletContext();
        //通过HttpServlet获取
        ServletContext context2 = this.getServletContext();

        System.out.println(context1);
        System.out.println(context2);

        System.out.println(context1==context2);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request,response);
    }
}
