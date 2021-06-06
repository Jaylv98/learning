package com.example.Servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;


/*

        获取请求参数通用方式：无论是get还是post请求方式都可以使用下列方法来获取请求参数
                String  getParameter(String name)：根据参数名称获取参数值
                String[]  getParameterValues(String name)：根据参数名称获取参数值的数组    多用于复选框
                Enumeration<String>  getParameterNames()：获取所有请求的参数名称
                Map<String,String[]> getParameterMap()：获取所有参数的map集合

*/

@WebServlet( value = "/Text07RequestParameter")
public class Text07RequestParameter extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post 获取请求参数


        //String  getParameter(String name)：根据参数名称获取参数值
        System.out.println("getParameter(String name)方法：");
        String username = request.getParameter("username");
        //System.out.println("post:");
        System.out.println(username);

        //String[]  getParameterValues(String name)：根据参数名称获取参数值的数组    多用于复选框
        System.out.println("getParameterValues(String name)方法：");
        String[] hobbies = request.getParameterValues("hobby");
        for (String hobby : hobbies) {
            System.out.println(hobby);
        }

        //Enumeration<String>  getParameterNames()：获取所有请求的参数名称
        System.out.println("getParameterNames()方法：");
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String name = parameterNames.nextElement();
            System.out.println(name);

            String value = request.getParameter(name);
            System.out.println(value);
            System.out.println("==============");
        }

        //Map<String,String[]> getParameterMap()：获取所有参数的map集合
        System.out.println("getParameterMap()方法：");
        Map<String, String[]> parameterMap = request.getParameterMap();
        //遍历
        Set<String> keyset = parameterMap.keySet();
        for (String name: keyset) {
            //根据键获取值
            String[] values = parameterMap.get(name);
            System.out.println(name);
            for (String value : values) {
                System.out.println(value);
            }
            System.out.println("=============");
        }

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get 获取请求参数

        //String  getParameter(String name)：根据参数名称获取参数值
       /* String username = request.getParameter("username");
        System.out.println("get:");
        System.out.println(username);*/

        this.doPost(request,response);
    }


}
