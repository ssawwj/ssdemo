package com.ss.ssdemo.controller.Aspect;

import com.alibaba.fastjson.JSON;
import com.ss.ssdemo.controller.annotation.LogAnnotation;
import com.ss.ssdemo.entity.Syslog;
import com.ss.ssdemo.service.SyslogService;
import com.ss.ssdemo.utils.HttpContextUtils;
import com.ss.ssdemo.utils.NetUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class LogAspect {
    @Autowired
    private SyslogService syslogService;
    @Autowired
    HttpSession session;

    @Pointcut("@annotation(com.ss.ssdemo.controller.annotation.LogAnnotation)")
    public void logPointCut(){

    }


    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        long start = System.currentTimeMillis();
        Object result = point.proceed();
        long time = System.currentTimeMillis()-start;
        saveLog(point,time);
        return result;
    }

    private void saveLog(ProceedingJoinPoint point,Long time){
        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method  = signature.getMethod();
        Syslog syslog = new Syslog();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        if(logAnnotation != null){
            syslog.setOperation(logAnnotation.value());
        }
        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();
        syslog.setMethod(new StringBuilder(className).append(".").append(methodName).append("()").toString());

        //获取请求参数
        Object [] args =point.getArgs();
        String params = JSON.toJSONString(args[0]);
        syslog.setParams(params);
        syslog.setIp(NetUtil.getIpAddr(HttpContextUtils.getHttpServletRequest()));
        syslog.setAddtime(new Date());
        syslog.setUsername(session.getAttribute("loginUser").toString());
        syslog.setTime(time);
        syslogService.insertSelective(syslog);
    }
}
