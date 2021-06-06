package cn.itcast.controller;


import cn.itcast.domain.Account;
import cn.itcast.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//账户web
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("findAll")
    public String findAll(Model model){
        System.out.println("表现层：查询所有账户信息");

        //调用service方法
        List<Account> list = accountService.findAll();
        model.addAttribute("list",list);
        return "list";
    }


    @RequestMapping("/save")
    public String save(Account account, HttpServletRequest request, HttpServletResponse response) throws  Exception{
        System.out.println("表现层：保存用户信息");

        accountService.saveAccount(account);
        response.sendRedirect(request.getContextPath()+"/account/findAll");
        return "save";
    }
}
