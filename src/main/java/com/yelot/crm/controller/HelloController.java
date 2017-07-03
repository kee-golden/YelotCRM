package com.yelot.crm.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by Gensis on 2016/9/9.
 */
@Controller
public class HelloController {

    // 从 application.properties 中读取配置，如取不到默认值为Hello
//    @Value("${application.hello:Hello}")
//    private String hello;


    @RequestMapping("/hello")
    public String helloJsp(Map<String, Object> map) {
        map.put("hello", "hello world,jsp,,,");
        return "helloJsp";

    }

}