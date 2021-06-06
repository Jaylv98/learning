package cn.itcast.exception;


import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//异常处理器
public class SysExceptionResolver implements HandlerExceptionResolver {


    @Override
    //处理异常逻辑
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        //获取异常对象
        SysException se=null;
        if(e instanceof SysException){//判断e是否是SysException对象的实例
            se=(SysException) e;
        }else {
            e =new SysException("系统正在维护");
        }
        //创建ModelAndView对象
        ModelAndView mv=new ModelAndView();
        mv.addObject("errorMsg",se.getMessage());
        mv.setViewName("error");//跳转的页面

        return mv;
    }
}
