package com.san.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.domain.vo
 * @className UserVO
 * @description
 * @date 2019/05/13 21:24
 */
@Data
public class UserVO implements Serializable {
    private static final long serialVersionUID = 8056423716258321480L;
    private Long id;
    private String userName;
    private String email;

    private String Redis_User_key;

    public UserVO() {
    }

    public UserVO(Long id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }
}
