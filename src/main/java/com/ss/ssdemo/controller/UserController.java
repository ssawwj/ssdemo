package com.ss.ssdemo.controller;

import com.ss.ssdemo.pojo.UserPojo;
import com.ss.ssdemo.service.UserService;
import com.ss.ssdemo.utils.EasyExcelUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author ss
 * @site www.molink.com
 * @company 乐搜科技有限公司
 * @create 2020-03-17 14:17
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("add")
    public String add(){

        return "user/add";
    }

    @RequestMapping("update")
    public String update(){

        return "user/update";
    }

    @RequestMapping("toLogin")
    public String toLogin(){

        return "user/login";
    }


    @RequestMapping("/unAuth")
    public String unAuth() {
        return "user/unAuth";
    }

    @RequestMapping("login")
    public String login(String username, String password, Model model){
        /**
         * 使用shiro编写认证操作
         */
        //获取Subject
        Subject subject = SecurityUtils.getSubject();
        //封装用户数据
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        try {
            //只要执行login方法，就会去执行UserRealm中的认证逻辑
            subject.login(token);

            //如果没有异常，代表登录成功
            //跳转到textThymeleaf页面，代表主页
            return "redirect:/toIndex.html";
        }catch (UnknownAccountException e) {
             e.printStackTrace();
             //登录失败
            model.addAttribute("msg","用户名不存在");
             return "user/login";
        }catch (IncorrectCredentialsException e) {
             e.printStackTrace();
             model.addAttribute("msg","密码错误");
             return "user/login";
        }
    }

    @GetMapping("excelExport")
    public void excelExport(HttpServletResponse response) throws IOException {
        String filename = "用户名单";
        String sheetName = "用户名单";
        List<UserPojo> list = userService.selectAll();
        EasyExcelUtil.excelExport(response,list,filename,sheetName);
    }
}
