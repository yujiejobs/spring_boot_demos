package com.san.controller;

import com.san.domain.entity.UserEntity;
import com.san.mongo.UserMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.controller
 * @className MongoDBController
 * @description
 * @date 2019/05/14 13:52
 */
@RestController
@RequestMapping("/mongo")
public class MongoDBController {

    @Autowired
    UserMongoRepository userRepository;


    /**
     * 创建对象
     */
    @GetMapping("/saveUser")
    public void saveUser() {
        UserEntity user = new UserEntity();
        user.setId(22L);
        user.setUserName("yu");
        user.setPassWord("123456");
        userRepository.saveUser(user);
    }

    /**
     * 根据用户名查询对象
     *
     * @return
     */
    @GetMapping("/findUserByUserName")
    public UserEntity findUserByUserName() {
        UserEntity user = userRepository.findUserByUserName("yu");
        return user;
    }

    /**
     * 更新对象
     */
    @GetMapping("/updateUser")
    public void updateUser() {
        UserEntity user = new UserEntity();
        user.setId(20L);
        user.setUserName("jie");
        user.setPassWord("000000");
        userRepository.updateUser(user);
    }

    /**
     * 删除对象
     */
    @GetMapping("/deleteUserById")
    public void deleteUserById() {
        userRepository.deleteUserById(20L);
    }

}
