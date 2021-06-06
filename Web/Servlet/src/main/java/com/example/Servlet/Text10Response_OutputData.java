package com.example.Servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;


/*
*
*               服务器向客户端输出数据
* */
@WebServlet(name = "Text10Response_OutputData", value = "/Text10Response_OutputData")
public class Text10Response_OutputData extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //告诉浏览器，服务器发送的消息体数据的编码，建议浏览器使用该编码解码(解决中文乱码问题)
        //response.setHeader("content-type","text/html;charset=utf-8");

        //简化代码(解决中文乱码问题)
        response.setContentType("text/html;charset=utf-8");

        //输出字符数据
        /*PrintWriter pw = response.getWriter();
        pw.write("你好，response");*/


        //输出字节数据
        ServletOutputStream sos = response.getOutputStream();
        sos.write("你好".getBytes("utf-8"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request,response);
    }
}
