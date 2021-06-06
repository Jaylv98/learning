package demo1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet( value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置编码
        request.setCharacterEncoding("utf-8");
        //获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //封装username
        User loginUser=new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);

        //调用UserDao的login方法
        UserDao dao=new UserDao();
        User user = dao.login(loginUser);

        //判断user
        if(user==null){
            //登录失败
            request.getRequestDispatcher("/FailServlet").forward(request,response);
        }else {
            //登录成功
            //存储数据
            request.setAttribute("user",user);
            //转发
            request.getRequestDispatcher("/SuccessServlet").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request, response);
    }
}
