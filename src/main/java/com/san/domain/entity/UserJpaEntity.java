package com.san.domain.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.domain.dao
 * @className UserDao
 * @description
 * @date 2019/05/13 21:11
 */
@Entity
@Data
public class UserJpaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String passWord;
    @Column(nullable = false, unique = true)
    private String email;
}
