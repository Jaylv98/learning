package com.itheima.service;

import com.itheima.domain.Role;
import com.itheima.domain.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {


    //查询所有用户信息
    List<Users> findAll(int size,int page);

    //保存用户信息
    void save(Users users);

    //根据id查询用户
    Users findById(Integer id);

    List<Role> findOtherRoles(Integer userId);

    void addRoleToUser(Integer userId, Integer[] roleIds);
}
