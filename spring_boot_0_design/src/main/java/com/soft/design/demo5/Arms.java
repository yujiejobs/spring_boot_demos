package com.soft.design.demo5;

import lombok.Data;

/**
 * 建造者模式: 复杂构造对象
 *
 * @author yujie
 * @date 2022/6/10 16:25
 */
@Data
public class Arms {
    /**
     * 头盔
     */
    private String helmet;
    /**
     * 铠甲
     */
    private String armor;
    /**
     * 武器
     */
    private String weapon;
}