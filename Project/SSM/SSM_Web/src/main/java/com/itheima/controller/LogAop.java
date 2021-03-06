package com.itheima.controller;


import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.security.Security;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;


    private Date visitTime;//开始时间
    private Class clazz;//访问的类
    private Method method;//访问的方法


    //前置通知---获取开始时间，执行的类，执行的方法
    @Before("execution(* com.itheima.controller.*.*(..))")
    public  void doBefore(JoinPoint jp)throws Exception{
        visitTime=new Date();//当前时间就是开始访问的时间
        clazz=jp.getTarget().getClass();//具体访问的类
        String methodName = jp.getSignature().getName();//获取访问的方法的名称
        Object[] args = jp.getArgs();//获取访问的方法的参数

        //获取具体执行的方法的method对象
        if (args==null||args.length==0){
            method=clazz.getMethod(methodName);//只能获取无参方法
        }else {
            Class[] classArgs=new Class[args.length];
            for(int i=0;i< args.length;i++){
                classArgs[i]=args[i].getClass();
            }
          method= clazz.getMethod(methodName, classArgs);
        }

    }



    //后置通知
    @After("execution(* com.itheima.controller.*.*(..))")
    public  void doAfter(JoinPoint jp){
        long time=new Date().getTime()-visitTime.getTime();//获取访问时长

        //获取url
        String url="";
        if(clazz!=null&&method!=null&&clazz!= LogAop.class){
            //获取类上的@RequestMapping("/orders")
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(classAnnotation!=null){
                String[] classValue = classAnnotation.value();

                //获取方法上的@RequestMapping("/addPermissionToRole")
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation!=null){
                    String[] methodValue = methodAnnotation.value();

                    url=classValue[0]+methodValue[0];

                    //获取访问的ip
                    String ip=request.getRemoteAddr();

                    //获取当前操作者的用户名
                    SecurityContext context= SecurityContextHolder.getContext();//从上下文中获取当前登录的用户
                    User user = (User)context.getAuthentication().getPrincipal();
                    String username = user.getUsername();


                    //将日志信息封装到SysLog对象
                    SysLog sysLog=new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);
                    sysLog.setMethod("[类名]"+clazz.getName()+"[方法名]"+method.getName());


                    //调用service完成操作
                    sysLogService.save(sysLog);
                }
            }
        }



    }


}
