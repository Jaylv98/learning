package cn.itcast.controller;


import cn.itcast.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {

    //响应返回值为String类型
    @RequestMapping("/testString")
    public String testString(Model model){
        System.out.println("testString方法已执行");
        //模拟从数据库中查询User对象
        User user=new User();
        user.setUsername("张三");
        user.setPassword("123");
        user.setAge(26);
        //model对象的addAttribute是将要添加的属性添加到RequestScope中，即添加到请求域中
        model.addAttribute("user",user);
        return "success";
    }

    //响应返回值为void类型
    /*
    *   手动转发不会使用视图解析器，路径需自己填写
    *   请求转发是一次请求，不用写项目名称
    *   重定向是两次请求，无法直接写/WEB-INF/pages/xxx.jsp，需写根目录下的文件
    * */
    @RequestMapping("/testVoid")
    public void testVoid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("testVoid方法已执行");
        //使用转发的方式
        //request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request,response);

        //使用它重定向的方式
        response.sendRedirect(request.getContextPath()+"/index.jsp");

        return;
    }


    //响应返回值为ModelAndView对象
    /*
    *       ModelAndView对象：
    *               可以传入视图的名称（即跳转的页面），还可以传入对象
    * */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        //创建ModelAndView对象
        ModelAndView mv=new ModelAndView();
        System.out.println("testModelAndView方法已执行");
        //模拟从数据库中查询出User对象
        User user=new User();
        user.setUsername("李四");
        user.setPassword("456");
        user.setAge(16);

        //将user对象存储到mv对象中，mv对象底层也会将user对象存入request域对象中
        mv.addObject("user",user);

        //跳转的页面
        mv.setViewName("success");
        return mv;
    }


    //使用关键字的方式进行转发或者重定向
    /*
    *   无法使用视图解析器
    *           固定语法：
    *               forward:xxx
    *               redirect:xxx
    * */
    @RequestMapping("/testForwardOrRedirect")
    public String testForwardOrRedirect(){
        System.out.println("testForwardOrRedirect方法已执行");
        //请求的转发
       // return "forward:/WEB-INF/pages/success.jsp";
        //重定向
        return "redirect:/index.jsp";

    }


    //响应json数据之发送ajax请求（模拟异步请求响应）
    @RequestMapping("/testAjax")
    public @ResponseBody User testAjax(@RequestBody User user){
        System.out.println("testAjax方法已执行");
        //客户端发送的是Ajax请求，传的是json字符串，后台将json字符串封装到User对象当中
        System.out.println(user);
        //做响应，模拟查询数据库
        user.setUsername("wangwu");
        user.setPassword("456");
        return user;

    }

}
