package com.example.Servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/*
*           ServletContext对象功能：
*                   获取文件的真实（服务器）路径：
*                       String  getRealPath(String path)
*
* */
@WebServlet(name = "Text15ServletContext_ServerPath", value = "/Text15ServletContext_ServerPath")
public class Text15ServletContext_ServerPath extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //由于目前都是本地项目，因此获取都是本地路径
        ServletContext context = request.getServletContext();
        //webapp目录下资源访问
        String a = context.getRealPath("/login.html");
        System.out.println(a);

        //WEB-INF目录下资源访问
        String b = context.getRealPath("/WEB-INF/web.xml");
        System.out.println(b);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request,response);
    }
}
