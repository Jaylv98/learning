package com.example.Servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/*

      ServletContext对象功能
                获取MIME类型：
                    MIME类型：在互联网通信过程中定义的一种文件数据类型
                        格式：大类型/小类型		text/html		image/jpeg
                    获取：
                        String getMimeType(String file)

*/

@WebServlet(name = "Text13ServletContext_MIME", value = "/Text13ServletContext_MIME")
public class Text13ServletContext_MIME extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取ServletContext对象
        ServletContext context = request.getServletContext();
        //定义文件名称
        String filename="a.jpg";
        //获取MIME类型
        String mimeType = context.getMimeType(filename);
        System.out.println(mimeType);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request,response);
    }
}
