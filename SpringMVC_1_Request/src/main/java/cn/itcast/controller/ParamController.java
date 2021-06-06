package cn.itcast.controller;


import cn.itcast.domain.Account;
import cn.itcast.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//请求参数绑定
@Controller
@RequestMapping("/param")
public class ParamController {

    /*
            请求参数基本类型绑定
                后台方法参数名称需与前端名称相同
    */
    @RequestMapping("/testParam")
    public String testParam(String username,String password){
        System.out.println("testParam方法已执行");
        System.out.println("username:"+username);
        System.out.println("password:"+password);
        return "success";
    }


    /*
    *       请求参数绑定实体类型(请求参数将数据封装到JavaBean的类中)
    * */
    @RequestMapping("/saveAccount")
    public String saveAccount(Account account){
        System.out.println("saveAccount 方法已执行");
        System.out.println(account);
        return "success";
    }


    /*
    *       自定义类型转换
    * */
    @RequestMapping("/saveUser")
    public String saveUser(User user){
        System.out.println("saveUser方法已执行");
        System.out.println(user);
        return "success";
    }


    //获取Serlvet原生的API
    @RequestMapping("/testServlet")
    public String testServlet(HttpServletRequest request, HttpServletResponse response){
        System.out.println("testServlet已执行");
        System.out.println("request:"+request);

        HttpSession session = request.getSession();
        System.out.println("session:"+session);

        ServletContext servletContext = session.getServletContext();
        System.out.println("servletContext:"+servletContext);

        System.out.println("response:"+response);
        return "success";
    }
}
