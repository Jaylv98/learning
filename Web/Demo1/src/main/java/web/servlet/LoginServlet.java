package web.servlet;

import domin.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置编码
        request.setCharacterEncoding("utf-8");


        //获取数据
        String verifycode = request.getParameter("verifycode");//获取用户填写的验证码


        //验证码校验
        HttpSession session = request.getSession();
        String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//确保验证码一次性使用

        if(!checkcode_server.equalsIgnoreCase(verifycode)){
            //验证码不正确
            //提示信息
            request.setAttribute("login_smg","验证码错误！");

            //跳转登陆页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }

        Map<String, String[]> map = request.getParameterMap();
        //封装user对象
        User user=new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        //调用Service查询
        UserService service=new UserServiceImpl();
        User loginUser=service.login(user);

        //判断是否登录成功
        if(loginUser!=null){
            //登录成功
            //将用户存入session
            session.setAttribute("user",loginUser);
            //跳转页面
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }else{
            //登录失败
            //提示信息
            request.setAttribute("login_smg","用户名或密码错误！");

            //跳转登陆页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;

        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
