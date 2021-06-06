package web.servlet;

import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DelUserServlet", value = "/DelUserServlet")
public class DelUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取id
        String id = request.getParameter("id");
        //调用service删除
        UserService service=new UserServiceImpl();
        service.deleteUser(id);

        //跳转到查询所有Servlet
        response.sendRedirect(request.getContextPath()+"/UserListServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
