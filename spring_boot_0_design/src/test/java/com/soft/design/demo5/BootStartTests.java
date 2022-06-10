package com.soft.design.demo5;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 描述: 建造者模式 案例
 *
 * @author yujie
 * @date 2022/6/1 13:57
 */
@Slf4j
@SpringBootTest
class BootStartTests {

    public Arms constructPerson(PersonBuilder pb) {
        pb.builderHelmetYanLong();
        pb.builderArmorMurder();
        pb.builderWeaponMurder();
        return pb.builderArms();
    }

    /**
     * 使用场景：
     * 1. 需要生成的对象具有复杂的内部结构。
     * 2. 需要生成的对象内部属性本身相互依赖。
     * 与工厂模式的区别是：建造者模式更加关注与零件装配的顺序。
     * JAVA 中的 StringBuilder就是建造者模式创建的，他把一个单个字符的char数组组合起来
     * Spring不是建造者模式，它提供的操作应该是对于字符串本身的一些操作，而不是创建或改变一个字符串。
     */
    @Test
    void cglibProxy() {
        BootStartTests bootStartTests = new BootStartTests();
        Arms arms = bootStartTests.constructPerson(new ArmsBuilder());
        System.out.println(arms.getHelmet());
        System.out.println(arms.getArmor());
        System.out.println(arms.getWeapon());
    }

}
