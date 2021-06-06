package com.itheima.controller;


import com.github.pagehelper.PageInfo;
import com.itheima.domain.Role;
import com.itheima.domain.Users;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                @RequestParam(name ="size",required = true,defaultValue = "5") Integer size){
        ModelAndView mv=new ModelAndView();
        List<Users> list=userService.findAll(page,size);
        //PageHelper提供的一个分页bean
        PageInfo pageInfo=new PageInfo(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }


    @RequestMapping("/save")
    public String save(Users users){
        userService.save(users);
        return "redirect:findAll";
    }


    @RequestMapping("/findById")
    public ModelAndView findById(Integer id){
        ModelAndView mv=new ModelAndView();
        Users users=userService.findById(id);
        mv.addObject("user",users);
        mv.setViewName("user-show");

        return mv;
    }


    //查询用户以及用户可以添加的角色
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) Integer userId){
        ModelAndView mv=new ModelAndView();
        //根据用户ID查询用户
        Users userInfo = userService.findById(userId);
        //根据用户ID查询可以添加的角色
        List<Role> otherRoles=userService.findOtherRoles(userId);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }


    //给用户添加角色
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) Integer userId,
                              @RequestParam(name = "ids",required = true) Integer[] roleIds){
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll";
    }
}
