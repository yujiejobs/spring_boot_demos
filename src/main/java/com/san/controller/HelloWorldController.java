package com.san.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
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
@Api("HelloWorldController 接口测试")
public class HelloWorldController {

    /**
     * Hello World
     *
     * @return
     */
    @ApiOperation(value = "hello", notes = "输出Hello World！")
    @GetMapping("/hello")
    public String hello() {
        return "Hello World！";
    }

}
