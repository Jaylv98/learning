package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.UserDao;
import com.itheima.domain.Role;
import com.itheima.domain.Users;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;




    @Override
    //查询所有用户信息
    public List<Users> findAll(int page,int size) {
          /*
                 PageHelper是mybatis的一个分页展示插件
                    pageNum:页码值；
                    pageSize:每页显示的条数
        */
        PageHelper.startPage(page,size);
        return userDao.findAll();
    }

    @Override
    public void save(Users users) {
        userDao.save(users);
    }


    @Override
    //根据id查询用户
    public Users findById(Integer id) {

        return userDao.findById(id);
    }

    @Override
    public List<Role> findOtherRoles(Integer userId) {
        return userDao.findOtherRoles(userId);
    }


    @Override
    public void addRoleToUser(Integer userId, Integer[] roleIds) {
        for(Integer roleId:roleIds){
            userDao.addRoleToUser(userId,roleId);
        }
    }


    /*
    *       SpringSecurity5.0之后需要给密码加密，否则会报Bad credentials错误
    *       解决方法：调用BCryptPasswordEncoder里的encode方法加密
    * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users=userDao.findByUsername(username);
        //创建SpringSecurity提供的User对象
        //User user=new User(users.getUsername(),passwordEncoder.encode(users.getPassword()),getAuthority(users.getRoles()));
        User user=new User(users.getUsername(),passwordEncoder.encode(users.getPassword()),users.getStatus()==0?false:true,
                true,true,true,getAuthority(users.getRoles()));
        return user;
    }



    //作用就是返回一个List集合，集合中装入的就是角色描述
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list=new ArrayList<>();
        for(Role role:roles){
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName().toUpperCase()));
        }
        return list;
    }




}
