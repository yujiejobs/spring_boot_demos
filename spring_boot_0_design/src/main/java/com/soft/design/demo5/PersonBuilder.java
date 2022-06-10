package com.soft.design.demo5;

/**
 * Builder接口: 给出一个抽象接口，以规范产品对象的各个组成成分的建造，这个接口只是规
 * 范
 *
 * @author yujie
 * @date 2022/6/10 16:27
 */
public interface PersonBuilder {

    void builderHelmetMurder();

    void builderArmorMurder();

    void builderWeaponMurder();

    void builderHelmetYanLong();

    void builderArmorYanLong();

    void builderWeaponYanLong();

    /**
     * 组装
     * @return Arms
     */
    Arms builderArms();
}