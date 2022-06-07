package com.soft.demo1;

/**
 * 描述: 策略模式接口
 *
 * @author yujie
 * @date 2022/6/1 13:47
 */
public interface IStrategy {

    /**
     * 获取到策略枚举
     *
     * @return 策略枚举
     */
    ResolveEnum gainFileType();

    /**
     * 处理方法
     *
     * @param object 参数
     */
    void handle(Object object);

}
