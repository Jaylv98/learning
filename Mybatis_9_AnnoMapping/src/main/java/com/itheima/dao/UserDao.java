package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


//在mybatis中，针对CRUD一共有四个注解 @Select @Insert @Update @Delete
//使用注解的方式给实体类起别名
public interface UserDao {

    //查询所有
    @Select("select * from user")
    @Results(id = "userMap",value={
            @Result(id = true,property = "userId",column = "id"),//主键id
            @Result(property = "userName",column = "username"),
            @Result(property = "userSex",column = "sex"),
            @Result(property = "userAddress",column = "address"),
            @Result(property = "userBirthday",column = "birthday"),
    })
    List<User> findAll();


    //查询单个用户信息
    @Select("select * from user where id=#{id}")
    @ResultMap("userMap")
    User findById(Integer userId);

    //根据用户名称模糊查询
    @Select("select * from user where username like #{username}")
    @ResultMap("userMap")
    List<User> findByName(String username);


}
