package com.example.Servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
*               案例：记住上一次访问时间
*                   需求：
*                       1.访问一个Servlet，如果是第一次访问，则提示：您好，欢迎您首次访问
*                       2.如果不是第一次访问，则提示：欢迎回来，您上次访问时间为：显示时间字符串
*                   分析：
*                       1.可以采用Cookie来完成
*                       2.在服务器中的Servlet判断是否有一个名为lastTime的cookie
*                           有：不是第一次访问
*                               响应数据：欢迎回来，您上次访问的时间为：xxxx
*                               写回Cookie：lastTime=xxxx
*                           没有：是第一次访问
*                               响应数据：您好，欢迎您首次访问
*                               写回Cookie：lastTime=xxx
*
* */
@WebServlet(name = "Text17CookieDemo", value = "/Text17CookieDemo")
public class Text17CookieDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置响应的消息体的数据格式以及编码
        response.setContentType("text/html;charset=utf-8");

        //获取所有cookie
        Cookie[] cookies = request.getCookies();
        boolean flag=false;//没有cookie为lastTime
        //遍历cookies数组
        if(cookies!=null&&cookies.length>0){
            for (Cookie cookie:cookies) {
                //获取cookie名称
                String name = cookie.getName();
                //判断名称是否为lastTime
                if("lastTime".equals(name)){
                    //有该cookie，不是第一次访问

                    flag=true;//有lastTime

                    //设置cookie的value
                    //获取当前时间的字符串，重新设置cookie的值，重新发送cookie
                    Date date=new Date();
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    String str_date = sdf.format(date);
                    //URL编码
                    str_date=URLEncoder.encode(str_date,"utf-8");

                    cookie.setValue(str_date);
                    //设置cookie的存活时间
                    cookie.setMaxAge(60*60*24*30);//一个月
                    response.addCookie(cookie);

                    //响应数据
                    //获取cookie的value，时间
                    String value = cookie.getValue();
                    //URL解码
                    System.out.println("解码前："+value);
                    value= URLDecoder.decode(value,"utf-8");
                    System.out.println("解码后："+value);
                    response.getWriter().write("<h1>欢迎回来，您上次返回的时间为："+value+"</h1>");
                    break;
                }
            }
        }

                if(cookies==null||cookies.length==0||flag==false){
                    //没有，第一次访问

                    //设置cookie的value
                    //获取当前时间的字符串，重新设置cookie的值，重新发送cookie
                    Date date=new Date();
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    String str_date = sdf.format(date);
                    //URL编码
                    System.out.println("编码前："+str_date);
                    str_date=URLEncoder.encode(str_date,"utf-8");
                    System.out.println("编码后："+str_date);

                    Cookie cookie=new Cookie("lastTime",str_date);
                    //设置cookie的存活时间
                    cookie.setMaxAge(60*60*24*30);//一个月
                    response.addCookie(cookie);


                    response.getWriter().write("<h1>您好，欢迎您首次访问</h1>");
                }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
