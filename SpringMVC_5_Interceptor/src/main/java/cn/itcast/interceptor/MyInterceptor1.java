package cn.itcast.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//自定义拦截器
public class MyInterceptor1 implements HandlerInterceptor {


    @Override
    /*
    *       预处理，在controller方法执行前拦截
    *           return true 放行，执行下一个拦截器，如果没有，执行controller中的方法
    *           return false 不放行
    * */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("MyInterceptor1对象已执行----预处理1111");
        //不放行，指定跳转的页面
        //request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request,response);
        return true;
    }


    @Override
    /*
    *       后处理，在controller方法执行后，success.jsp执行之前拦截
    * */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor1对象已执行----后处理1111");
        //指定跳转的页面
        request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request,response);
    }


    @Override
    /*
    *   success.jsp页面执行之后，执行该方法
    *
    * */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyInterceptor1对象已执行----终1111");
    }
}
