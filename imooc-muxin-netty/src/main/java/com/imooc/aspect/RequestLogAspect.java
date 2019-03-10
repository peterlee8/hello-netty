package com.imooc.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author peterLee
 * @Date 2019/1/21 2:39 PM
 * @Describtion :
 */
@Aspect
@Component
public class RequestLogAspect {

    @After("@annotation(com.imooc.anno.RequestLog)")
    public void excutors(JoinPoint joinPoint){
        //获取请求的属性
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取请求对象
        HttpServletRequest request = attributes.getRequest();

        System.out.println("url="+request.getRequestURI());//url
        System.out.println("method="+request.getMethod());//方法
        System.out.println(request.getRemoteAddr());//请求地址
        //参数
        System.out.println(joinPoint.getArgs());
    }
}
