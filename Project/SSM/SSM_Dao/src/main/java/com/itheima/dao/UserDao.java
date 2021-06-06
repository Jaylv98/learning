package com.itheima.dao;

import com.itheima.domain.Role;
import com.itheima.domain.Users;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserDao {

    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "email",column = "email"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.dao.RoleDao.findRoleByUserId")),
    })
    public Users findByUsername(String username);


    @Select("select * from users")
    //查询所有用户信息
    List<Users> findAll();


    //保存用户信息
    @Insert("insert into users (email,username,password,phoneNum,status) " +
            "values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(Users users);


    //根据id查询用户
    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "email",column = "email"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.dao.RoleDao.findRoleByUserId")),
    })
    Users findById(Integer id);



    @Select("select * from role where id not in(select roleId from users_role where userid=#{userid}) ")
    List<Role> findOtherRoles(Integer userId);

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
}
