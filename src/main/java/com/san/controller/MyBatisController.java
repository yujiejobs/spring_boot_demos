package com.san.controller;

import com.san.domain.entity.UserEntity;
import com.san.mapper.UserMyBatisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.controller
 * @className MyBatisController
 * @description
 * @date 2019/05/14 0:47
 */
@RestController
@RequestMapping("mybatis")
public class MyBatisController {

    @Autowired
    UserMyBatisMapper userMyBatisMapper;

    @GetMapping("getAll")
    public List<UserEntity> getAll() {
        return userMyBatisMapper.getAll();
    }

    @GetMapping("getOne")
    public UserEntity getOne() {
        return userMyBatisMapper.getOne(1L);
    }

    @GetMapping("insert")
    public void insert(UserEntity user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("99999");
        userEntity.setPassWord("88888");
        userEntity.setEmail("88888@qq.com");
        userMyBatisMapper.insert(userEntity);
    }

    @GetMapping("update")
    public void update() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUserName("big");
        userMyBatisMapper.update(userEntity);
    }

    @GetMapping("delete")
    public void delete() {
        userMyBatisMapper.delete(1L);
    }
}
