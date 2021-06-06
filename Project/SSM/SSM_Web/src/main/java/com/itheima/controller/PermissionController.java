package com.itheima.controller;


import com.github.pagehelper.PageInfo;
import com.itheima.domain.Permission;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                @RequestParam(name ="size",required = true,defaultValue = "5") Integer size){

        ModelAndView mv=new ModelAndView();
        List<Permission> list=permissionService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("permission-list");
        return mv;

    }

    @RequestMapping("/save")
    public String save(Permission permission){
        permissionService.save(permission);
        return "redirect:findAll";
    }

}
