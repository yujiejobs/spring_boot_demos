package com.soft.design.demo4;

/**
 * CGLIB动态代理: 代理接口实现
 *
 * @author yujie
 * @date 2022/6/10 15:59
 */
public class UserDaoImpl implements UserDao {

    @Override
    public void save() {
        System.out.println("保存数据方法");
    }

}
