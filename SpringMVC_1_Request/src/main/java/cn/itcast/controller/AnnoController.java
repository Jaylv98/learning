package cn.itcast.controller;

import cn.itcast.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//常用的注解
@Controller
@RequestMapping("/anno")
public class AnnoController {


    /*
    *   @RequestParam:（将请求参数绑定到控制器的方法上）修饰当前方法的参数
    *           name(value)：请求参数名
    *           required：是否包含该参数，默认值为true，表示请求参数中必须包含该参数，不包含就报错
    *           defaultValue：默认参数值，如果设置了该值，required=true将失效，自动为false,如果没有传该参数，就使用默认值
    * */
    @RequestMapping("/testRequestParam")
    public String testRequestParam(@RequestParam(name = "name") String username){
        System.out.println("testRequestParam方法已执行");
        System.out.println(username);
        return "success";
    }

    /*
    *       获取请求体的内容：
    *           @RequestBody：用来接收前端传递给后端的json字符串中的数据的(请求体中的数据的)
    *                   get方法没有请求体，所以使用@RequestBody接收数据时，只能使用post方式提交请求
    *
    * */
    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String body){
        System.out.println("testRequestBody方法已执行");
        System.out.println(body);

        return "success";
    }


    /*
    *     restful风格的URL：
    *       @PathVariable("xxx"):
    *           将URL中占位符参数{xxx}绑定到处理器类的方法形参中
    * */
    @RequestMapping("/testPathVariable/{uid}")
    public String testPathVariable(@PathVariable(name = "uid") String id){
        System.out.println("testPathVariable方法已执行");
        System.out.println(id);
        return "success";
    }


    /*
    *   @RequestHeader：获取请求头信息
    *           value：请求头的名称
    * */
    @RequestMapping("/testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "Accept") String header){
        System.out.println("testRequestHeader方法已执行");
        System.out.println(header);
        return "success";
    }



    /*
    *   @CookieValue:将指定cookie名称的值传入控制器方法参数
    *       value：指定cookie的名称
    *
    * */
    @RequestMapping("/testCookieValue")
    public String testCookieValue(@CookieValue(value = "JSESSIONID") String cookie){
        System.out.println("testCookieValue已执行");
        System.out.println(cookie);
        return "success";
    }


    /*
    *       @ModelAttribute:用于修饰方法和参数
    *                       出现在方法上，表示当前方法会在控制器方法执行之前执行
    *                       出现在参数上，获取指定的数据给参数赋值
    *               value：用于获取数据的key
    *
    *           应用场景：
    *                   当表单提交数据不是完整的实体类数据时，保证没有提交数据的字段使用数据库对象原来的数据。
    *                   例如：
    *                       在编辑一个用户时，用户有一个创建信息字段，该字段的值是不允许被修改的。在提交表单数
    *                        据是肯定没有此字段的内容，一旦更新会把该字段内容置为 null，此时就可以使用此注解解决问题。
    *
    * */
    @RequestMapping("/testModelAttribute")
    public String testModelAttribute(User user){
        System.out.println("testModelAttribute方法已执行");
        System.out.println(user);
        return "success";
    }
    @ModelAttribute
    public void showModelAttribute(){
        System.out.println("showModelAttribute方法执行");
    }
}
