package com.san.domain.vo;

import lombok.Data;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.domain.vo
 * @className UserVO
 * @description
 * @date 2019/05/13 21:24
 */
@Data
public class UserVO {
    private Long id;
    private String userName;
    private String email;
}
