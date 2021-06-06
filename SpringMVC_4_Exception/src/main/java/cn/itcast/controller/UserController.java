package cn.itcast.controller;


import cn.itcast.exception.SysException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/testException")
    public String testException()throws Exception{
        System.out.println("testException方法已执行");

        try {
            //模拟异常
            int a=1/0;
        } catch (Exception e) {
            e.printStackTrace();
            //抛出自定义异常信息
            throw new SysException("系统功能异常");

        }

        return "success";

    }
}
