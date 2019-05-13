package com.san.service.iml;

import com.san.domain.entity.UserJpaEntity;
import com.san.mapper.UserJpaMapper;
import com.san.service.UserJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.service
 * @className UserServiceImpl
 * @description
 * @date 2019/05/13 21:16
 */
@Service
public class UserJpaServiceImpl implements UserJpaService {

    @Autowired
    UserJpaMapper userJpaMapper;

    @Override
    public UserJpaEntity findByUserName(String userName) {
        return userJpaMapper.findByUserName(userName);
    }

    @Override
    public UserJpaEntity findByUserNameOrEmail(String username, String email) {
        return userJpaMapper.findByUserNameOrEmail(username,email);
    }
}
