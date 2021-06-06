package web.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//登录验证的过滤器
@WebFilter(value = "/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //强制转换
        HttpServletRequest request1=(HttpServletRequest) request;

        //获取资源请求路径
        String uri = request1.getRequestURI();
        //判断是否包含登录相关路径,要注意排除css/js/图片/验证码等资源
        if(uri.contains("/login.jsp")||uri.contains("/LoginServlet")||uri.contains("/css/")||uri.contains("/js/")||uri.contains("/fonts/")||uri.contains("/CheckCodeServlet")){
            //包含，用户就是想登录，放行
            chain.doFilter(request, response);
        }else {
            //不包含，需要验证用户是否登录
            //从session中获取user
            Object user = request1.getSession().getAttribute("user");
            if(user!=null){
                //登录了，放行
                chain.doFilter(request, response);
            }else {
                //没有登录，跳转登录页面
                request1.setAttribute("login_msg","您尚未登录，请登录");
                request1.getRequestDispatcher("/login.jsp").forward(request1,response);
            }
        }

        //chain.doFilter(request, response);
    }
}
