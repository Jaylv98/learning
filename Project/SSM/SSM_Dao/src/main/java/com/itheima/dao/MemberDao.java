package com.itheima.dao;

import com.itheima.domain.Member;
import org.apache.ibatis.annotations.Select;

//会员表
public interface MemberDao {

    //根据id查询会员信息
    @Select("select * from member where mid=#{mid}")
    public Member findById(Integer mid)throws Exception;
}
