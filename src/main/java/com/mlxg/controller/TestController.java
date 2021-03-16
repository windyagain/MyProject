package com.mlxg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "this is a test";
    }

    @RequestMapping("/test2")
    public String test2(){
        return "test_page";
    }


    @RequestMapping("/userList")
    public String userList(){
        return "user_list";
    }
}
