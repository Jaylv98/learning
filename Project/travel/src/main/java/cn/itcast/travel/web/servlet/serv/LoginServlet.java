package cn.itcast.travel.web.servlet.serv;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


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
        UserService service=new UserServiceImpl();
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
