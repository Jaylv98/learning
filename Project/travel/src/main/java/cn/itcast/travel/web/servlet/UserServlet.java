package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet( "/user/*")
public class UserServlet extends BaseServlet {

    //声明UserService业务对象
    private UserService service=new UserServiceImpl();

    //注册功能
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //验证码校验
        String check = request.getParameter("check");
        //从session中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//保证验证码只能使用一次

        //比较
        if(checkcode_server==null || !checkcode_server.equalsIgnoreCase(check)){
            //验证码错误
            ResultInfo info=new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");

            //将info对象序列化为json
            ObjectMapper mapper=new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);

            return;
        }

        //获取数据
        Map<String, String[]> map = request.getParameterMap();

        //封装对象
        User user=new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用service完成注册

        boolean flag=service.regist(user);
        ResultInfo info=new ResultInfo();
        //响应结果
        if(flag){
            //注册成功
            info.setFlag(true);
        }else{
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败");
        }

        //将info对象序列化为json
        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(info);

        //将json数据写回客户端
        //设置content-type
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);


    }


    //登录功能
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //验证码校验
        String check = request.getParameter("check");
        //从session中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//保证验证码只能使用一次

        //比较
        if(checkcode_server==null || !checkcode_server.equalsIgnoreCase(check)){
            //验证码错误
            ResultInfo info=new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");

            //将info对象序列化为json
            ObjectMapper mapper=new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);

            return;
        }





        //获取用户名和密码数据
        Map<String, String[]> map = request.getParameterMap();

        //封装User对象
        User user=new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //调用service查询

        User u=service.login(user);

        ResultInfo info=new ResultInfo();

        //判断用户对象是否为null
        if(u==null){
            //用户名密码错误
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }

        //判断用户是否激活
        if(u!=null&&!"Y".equals(u.getStatus())){
            //用户未激活
            info.setFlag(false);
            info.setErrorMsg("您尚未激活，请激活");
        }

        //判断登陆成功
        if(u!=null&&"Y".equals(u.getStatus())){
            //登陆成功
            info.setFlag(true);
            request.getSession().setAttribute("user",u);
        }

        //响应数据
        ObjectMapper mapper=new ObjectMapper();

        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }



    //查询单个对象
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //从session中获取登录用户
        Object user = request.getSession().getAttribute("user");
        //将user写回客户端
       /* ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),user);*/

        writeValue(user,response);
    }



    //退出功能
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //销毁session
        request.getSession().invalidate();

        //跳转登录页面
        response.sendRedirect(request.getContextPath()+"/login.html");
    }



    //激活功能
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //获取激活码
        String code = request.getParameter("code");
        if(code!=null){
            //调用servlet完成激活
            UserService service=new UserServiceImpl();
            boolean flag=service.active(code);

            //判断标记
            String msg=null;
            if(flag){
                //激活成功
                msg="激活成功，请<a href='login.html'>登录</a>";
            }else{
                //激活失败
                msg="激活失败，请联系管理员";
            }
            response.setContentType("text/html;charset=utf-8");//预防乱码
            response.getWriter().write(msg);

        }
    }

}
