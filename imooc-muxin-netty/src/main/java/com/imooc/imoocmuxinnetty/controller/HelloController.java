package com.imooc.imoocmuxinnetty.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author peterLee
 * @Date 2018/10/8 下午5:45
 * @Describtion :
 */
@RestController
public class HelloController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
