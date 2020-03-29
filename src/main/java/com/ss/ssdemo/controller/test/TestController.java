package com.ss.ssdemo.controller.test;

import com.ss.ssdemo.service.impl.SyslogServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class TestController {
    @Autowired
    private SyslogServiceImpl syslogService;

    @RequestMapping("async")
    public void testAsync() {
        long start = System.currentTimeMillis();
        log.info("异步方法开始");

        syslogService.asyncMethod();

        log.info("异步方法结束");
        long end = System.currentTimeMillis();
        log.info("总耗时：{} ms", end - start);
    }

    @RequestMapping("sync")
    public void testSync() {
        long start = System.currentTimeMillis();
        log.info("同步方法开始");

        syslogService.syncMethod();

        log.info("同步方法结束");
        long end = System.currentTimeMillis();
        log.info("总耗时：{} ms", end - start);
    }
}
