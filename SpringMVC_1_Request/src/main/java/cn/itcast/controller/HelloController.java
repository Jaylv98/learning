package cn.itcast.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//控制器类
@Controller
public class HelloController {


    /*
    *   @RequestMapping
    *           作用：用于建立请求URL和处理请求方法之间的对应关系
    *           属性：
    *               path/value：指定请求的URL
    *               method：当前方法可以接收哪种请求方式
    *                       RequestMethod.GET
    *                       RequestMethod.POST
    *                       RequestMethod.DELETE
    *                       ......
    *               params:限制请求参数的条件
    *                       例如：
    *                            params = {"accountName"}，表示请求参数必须有 accountName
    *                            params = {"money!100"}，表示请求参数中 money 不能是 100。
    * */
    @RequestMapping(path = "/hello",method = {RequestMethod.GET})
    public String sayHello(){
        System.out.println("Hello,SpringMVC");
        return "success";
    }
}
