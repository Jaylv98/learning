package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private RouteService routeService=new RouteServiceImpl();
    private FavoriteService favoriteService=new FavoriteServiceImpl();

    //分页查询
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //接收参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");

        //接收对应的rname线路名称
        String rname = request.getParameter("rname");

        rname=new String(rname.getBytes("iso-8859-1"),"utf-8");//解决rname乱码问题



        //处理参数
        //类别ID
        int cid=0;
        if(cidStr!=null&&cidStr.length()>0&&!"null".equals(cidStr)){
            cid=Integer.parseInt(cidStr);
        }

        //当前页码，如果不传递，默认第一页
        int currentPage=0;
        if(currentPageStr!=null&&currentPageStr.length()>0){
            currentPage=Integer.parseInt(currentPageStr);
        }else{
            currentPage=1;
        }

        //每页显示条数，如果不传递，默认每页显示5条记录
        int pageSize=0;
        if(pageSizeStr!=null&&pageSizeStr.length()>0){
            pageSize=Integer.parseInt(pageSizeStr);
        }else{
            pageSize=5;
        }


        // 调用service查询PageBean对象
        PageBean<Route> pb = routeService.pageQuery(cid, currentPage, pageSize,rname);

        //将pageBean对象序列化为json，返回
        writeValue(pb,response);

    }


    //根据id查询一个旅游线路的详细信息
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //接收id
        String rid = request.getParameter("rid");

        //调用service查询route对象
        Route route=routeService.findOne(rid);
        //转为json写回客户端
        writeValue(route,response);

    }


    //判断当前用户是否收藏过该线路
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取线路id
        String rid = request.getParameter("rid");

        //获取当前登录的用户
        User user = (User)request.getSession().getAttribute("user");
        int uid;
        if(user==null){
            //用户尚未登录
            uid=0;
        }else{
            //用户已经登录
            uid = user.getUid();
        }

        //调用FavoriteService查询是否收藏
        boolean flag = favoriteService.isFavorite(rid, uid);

        //写回客户端
        writeValue(flag,response);
    }


    //添加收藏
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取线路rid
        String rid = request.getParameter("rid");
        //获取当前登录的用户
        User user = (User)request.getSession().getAttribute("user");
        int uid;
        if(user==null){
            //用户尚未登录
            return;
        }else{
            //用户已经登录
            uid = user.getUid();
        }

        //调用service添加
        favoriteService.add(rid,uid);

    }

    }
