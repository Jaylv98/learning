package web.servlet;

import domin.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DelSelectedServlet", value = "/DelSelectedServlet")
public class DelSelectedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取所有id
        String[] ids = request.getParameterValues("uid");
        //调用service删除
        UserService service=new UserServiceImpl();
        service.delSelectedUser(ids);

        //跳转查询所有Servlet
        response.sendRedirect(request.getContextPath()+"/UserListServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
