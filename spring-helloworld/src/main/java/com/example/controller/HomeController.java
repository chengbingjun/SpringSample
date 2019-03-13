package com.example.controller;

import com.example.aop.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userServiceImpl;

    @RequestMapping("/hello")
    public String hello() {
        userServiceImpl.addUser();
        userServiceImpl.deleteUser();
        //return jsp的文件名就可以跳转到对应的jsp
        return "hello";
    }
}
