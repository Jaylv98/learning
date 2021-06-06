package cn.itcast.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("testInterceptor")
    public String testInterceptor(){
        System.out.println("testInterceptor方法已执行");

        return "success";
    }

}
