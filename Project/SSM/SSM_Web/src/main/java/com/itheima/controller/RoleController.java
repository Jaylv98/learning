package com.itheima.controller;


import com.github.pagehelper.PageInfo;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                @RequestParam(name ="size",required = true,defaultValue = "5") Integer size){
        ModelAndView mv=new ModelAndView();
        List<Role> roleList=roleService.findAll(page,size);
        //pageHelper提供的一个分页bean
        PageInfo pageInfo=new PageInfo(roleList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Role role){
        roleService.save(role);
        return "redirect:findAll";
    }


    //根据roleId查询角色，并查询出可以添加的权限
    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true) Integer roleId){
        ModelAndView mv=new ModelAndView();
        //根据roleId查询角色
        Role role=roleService.findById(roleId);
        //根据roleId查询可以添加的权限
        List<Permission> otherPermission=roleService.findOtherPermission(roleId);
        mv.addObject("role",role);
        mv.addObject("permissionList",otherPermission);
        mv.setViewName("role-permission-add");
        return mv;
    }


    //给角色添加权限
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true) Integer roleId,
                                      @RequestParam(name = "ids",required = true) Integer[] permissionIds){

        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll";

    }

}
