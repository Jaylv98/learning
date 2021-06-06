package cn.itcast.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //完成方法分发
        //获取请求路径
        String uri = req.getRequestURI();
        System.out.println("uri请求路径:"+uri);

        //获取方法名称
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);
        System.out.println("方法:"+methodName);
        //获取方法对象
        try {
            //获取方法
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //执行方法
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    //直接将传入得对象序列化为json，并写回客户端
    public void writeValue(Object obj,HttpServletResponse response) throws IOException {

        ObjectMapper mapper=new ObjectMapper();

        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),obj);
    }


    //将传入的对象序列化为json，返回给调用者
    public String writeValueAsString(Object obj) throws JsonProcessingException {

        ObjectMapper mapper=new ObjectMapper();

        return mapper.writeValueAsString(obj);

    }

}
