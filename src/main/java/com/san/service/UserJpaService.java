package com.san.service;

import com.san.domain.entity.UserJpaEntity;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.service
 * @className UserService
 * @description
 * @date 2019/05/13 21:16
 */
public interface UserJpaService {
    /**
     * 通过userName查询用户
     *
     * @param userName
     * @return
     */
    UserJpaEntity findByUserName(String userName);

    /**
     * 通过username 与 email  查询数据
     *
     * @param username
     * @param email
     * @return
     */
    UserJpaEntity findByUserNameOrEmail(String username, String email);
}
