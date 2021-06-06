package com.example.Servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/*

        请求转发：一种在服务器内部的资源跳转方式
                步骤：
                    1.通过request对象获取请求转发器对象：RequestDispatcher getRequestDispatcher(String path)
                    2.使用RequestDispatcher对象来进行转发：forward(ServletRequest request,ServletResponse response)
                特点：
							1.浏览器地址栏路径无变化
							2.只能转发到当前服务器内部资源中
							3.转发是一次请求

		共享数据：
				域对象：一个有作用范围的对象，可以在范围内共享数据
				request域：代表一次请求的范围，一般用于请求转发的多个资源中共享数据
				方法：
							void setAttribute(String name,Object obj)：存储数据
							Object setAttribute(String name)：通过键获取值
							void removeAttribute(String name)：通过键移除键值对

    【注意】：只有在转发的情况下才能使用request域来共享数据
*/

@WebServlet(value = "/Text08RequestForward_1")
public class Text08RequestForward_1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Text08RequestForward_1访问成功");

        //存储数据到request域中
        request.setAttribute("msg","Hello");

        //转发到Text08RequestForward_2
       /* RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Text08RequestForward_2");
        requestDispatcher.forward(request,response);*/
        request.getRequestDispatcher("/Text08RequestForward_2").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request,response);

    }
}
