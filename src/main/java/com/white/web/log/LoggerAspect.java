package com.white.web.log;

import com.white.security.model.AuthUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

/**
 * 日志切入类
 */
@Aspect
@Component
@Order(5)
public class LoggerAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    // 定义切点
    @Pointcut("execution(* com.white.web.controller.*.*(..))")
    public void controllerLog(){}

    // 切入方法
    @AfterThrowing(pointcut = "controllerLog()",throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e){
        LogInfo log = new LogInfo();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        AuthUser user =  (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // AuthUser user = (AuthUser) request.getSession().getAttribute("user");
        if (user!=null) {
            log.setUserId(user.getUsername());
        }
        // 当前的操作用户
        String createUser = user.getUsername();
        // 请求的IP
        String requestIp = request.getRemoteAddr();
        // 请求方法
        String method = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()";
        /*========控制台输出=========*/
        System.out.println("=====异常通知开始=====");
        System.out.println("异常URL:" + request.getRequestURL().toString());
        System.out.println("异常代码:" + e.getClass().getName());
        System.out.println("异常方法:" + method);
        System.out.println("请求人:" + createUser);
        System.out.println("请求IP:" + requestIp);
        System.out.println("请求参数:" + joinPoint.getArgs());
        System.out.println("操作时间:" + new Date());
        System.out.println("异常信息:" + e.getMessage());
        /*==========数据库日志=========*/
        // 这下面可以将日志写入到数据库中
        // loading...
    }
}