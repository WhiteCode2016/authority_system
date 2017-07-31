package com.white.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("/testExcetion")
    public String hello() throws Exception {
        throw new Exception("发生错误");
    }

}