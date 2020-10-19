package com.dingtalk.edu.openapi.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
 
    @RequestMapping("/")
    public String sayHello() {
        return "Hello world.";
    }
}
