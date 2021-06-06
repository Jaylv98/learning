package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


//在mybatis中，针对CRUD一共有四个注解 @Select @Insert @Update @Delete
public interface UserDao {

    //查询所有
    @Select("select * from user")
    List<User> findAll();

    //保存用户
    @Insert("insert into user(username,address,sex,birthday) values(#{username},#{address},#{sex},#{birthday})")
    void saveUser(User user);

    //更新用户信息
    @Update("update user set username=#{username},sex=#{sex},address=#{address},birthday=#{birthday} where id=#{id}")
    void updateUser(User user);

    //删除用户
    @Delete("delete from user where id=#{id}")
    void deleteUser(Integer userId);

    //查询单个用户信息
    @Select("select * from user where id=#{id}")
    User findById(Integer userId);

    //根据用户名称模糊查询
    //@Select("select * from user where username like #{username}")
    @Select("select * from user where username like '%${value}%'")
    List<User> findByName(String username);

    //查询总用户数
    @Select("select count(*) from user")
    int findTotalUser();
}
