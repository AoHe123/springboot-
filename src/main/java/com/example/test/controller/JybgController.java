package com.example.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@RequestMapping("/api/users")
@Controller
public class JybgController {

    @PutMapping(value = "list")
    public String List(){
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
       return "111";
    }

}
