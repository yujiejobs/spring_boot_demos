package com.san.domain.entity;

import lombok.Data;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.domain.entity
 * @className UserEntity
 * @description
 * @date 2019/05/14 0:38
 */
@Data
public class UserEntity {
    private Long id;
    private String userName;
    private String passWord;
    private String email;
}
