package com.san.mapper;

import com.san.domain.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.mapper
 * @className UserMapper
 * @description
 * @date 2019/05/13 21:14
 */
public interface UserJpaMapper extends JpaRepository<UserJpaEntity, Long> {
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
