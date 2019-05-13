package com.san.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.controller
 * @className HelloWorldController
 * @description
 * @date 2019/05/13 20:27
 */
@RestController
public class HelloWorldController {

    /**
     * Hello World
     *
     * @return
     */
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World！";
    }

}
