package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

public interface RouteService {
     PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname);

     //根据id查询一个旅游线路的详细信息
     public Route findOne(String rid);

}
