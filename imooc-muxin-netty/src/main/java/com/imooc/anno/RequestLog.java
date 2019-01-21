package com.imooc.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @Author peterLee
 * @Date 2019/1/21 2:37 PM
 * @Describtion :
 */
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface RequestLog {
    String value() default "";
}
