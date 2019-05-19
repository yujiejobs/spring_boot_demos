package com.san.controller;

import com.san.common.aspect.Timer;
import com.san.common.base.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.controller
 * @className GlobalExceptionController
 * @description 系统error处理
 * @date 2019/05/19 23:13
 */
@RestController
@Api("GlobalExceptionController")
public class GlobalExceptionController {

    @Timer
    @ApiOperation(value = "error")
    @GetMapping("/error")
    public Message error() {
        int a = 1/0;
        return Message.success();
    }

}
