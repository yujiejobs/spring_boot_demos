//package com.san.controller;
//
//import com.san.domain.entity.UserJpaEntity;
//import com.san.service.UserJpaService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author yujie
// * @projectName spring_boot_init
// * @packageName com.san.controller
// * @className UserJpaController
// * @description
// * @date 2019/05/13 21:18
// */
//@RestController
//@RequestMapping("userJpa")
//public class UserJpaController {
//
//    @Autowired
//    UserJpaService userJpaService;
//
//    /**
//     * 通过userName查询用户
//     *
//     * @param userName
//     * @return
//     */
//    @GetMapping("findByUserName")
//    UserJpaEntity findByUserName(String userName) {
//        return userJpaService.findByUserName(userName);
//    }
//
//    /**
//     * 通过username 与 email  查询数据
//     *
//     * @param username
//     * @param email
//     * @return
//     */
//    @GetMapping("findByUserNameOrEmail")
//    UserJpaEntity findByUserNameOrEmail(String username, String email) {
//        return userJpaService.findByUserNameOrEmail(username, email);
//    }
//
//}
