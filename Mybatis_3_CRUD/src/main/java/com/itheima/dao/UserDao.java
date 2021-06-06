package com.itheima.dao;

import com.itheima.domain.QueryVo;
import com.itheima.domain.User;

import java.util.List;

//用户的持久层接口
public interface UserDao {

    //查询所有用户
    List<User> findAll();

    //保存用户信息
    void saveUser(User user);

    //修改用户信息
    void updateUser(User user);

    //删除用户信息
    void deleteUser(Integer userId);

    //查询单个用户信息
    User findById(Integer userId);

    //根据名称模糊查询用户信息
    List<User> findByName(String username);

    //查询总用户数
    int findTotal();

    //根据QueryVo中的条件查询用户
    List<User> findUserByVo(QueryVo vo);

    //根据条件查询用户信息
    List<User> findUserByCondition(User user);

    //根据id查询多个用户
    List<User> findUserInIds(QueryVo vo);
}
