package com.san.domain.entity;


import lombok.Data;

import javax.persistence.*;
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
@Table(name = "user")
public class UserJpaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * @GeneratedValue注解的strategy属性提供四种值： –AUTO： 主键由程序控制，是默认选项，不设置即此项。
     * <p>
     * –IDENTITY：主键由数据库自动生成，即采用数据库ID自增长的方式，Oracle不支持这种方式。
     * <p>
     * –SEQUENCE：通过数据库的序列产生主键，通过@SequenceGenerator 注解指定序列名，mysql不支持这种方式。
     * <p>
     * –TABLE：通过特定的数据库表产生主键，使用该策略可以使应用更易于数据库移植。
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String passWord;
    @Column(nullable = false, unique = true)
    private String email;
}
