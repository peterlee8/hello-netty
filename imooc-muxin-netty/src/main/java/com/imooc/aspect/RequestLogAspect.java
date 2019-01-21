package com.imooc.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Author peterLee
 * @Date 2019/1/21 2:39 PM
 * @Describtion :
 */
@Aspect
@Component
public class RequestLogAspect {

    @After("@annotation()")
}
