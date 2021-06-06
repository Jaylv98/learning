package com.itheima.dao;

import com.itheima.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TravellerDao {

    //根据orderId查询
    @Select("select * from traveller where name in (select travellerId from order_traveller where orderId= #{orderId} )")
    public List<Traveller> findByOrderId(String orderId);
}
