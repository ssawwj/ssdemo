package com.ss.ssdemo.controller;

import com.ss.ssdemo.controller.annotation.LogAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author ss
 * @site www.molink.com
 * @company 乐搜科技有限公司
 * @create 2020-03-09 15:55
 */
@Controller
@Slf4j
@Component
public class HelloController {

    @RequestMapping(value={"","/toIndex"})
    @LogAnnotation("登陆日志")
    public String toIndex(Model model, HttpSession session){
        session.setAttribute("loginUser","书生");
        model.addAttribute("msg","欢迎您1111222");
        log.info("这是日志");
        return "index";
    }


    @Scheduled(cron= "0 */1 * * * ?")
    public void startjob(){
        log.info("定时任务开启");
    }

    @Scheduled(cron= "0 */2 * * * ?")
    public void endjob(){
        log.info("定时任务结束");
    }


}
