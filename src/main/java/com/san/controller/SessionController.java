package com.san.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.controller
 * @className SessionController
 * @description
 * @date 2019/05/13 23:15
 */
@RequestMapping("session")
public class SessionController {
    @GetMapping("/uid")
    public void uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        System.out.println("one: " + session.getId());
        //登录 Redis 输入 keys '*sessions*'
    }
}
