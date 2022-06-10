package com.soft.design.demo4.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 描述: JDK代理
 *
 * @author yujie
 * @date 2022/6/10 16:14
 */
public class InvocationHandlerImpl implements InvocationHandler {
    /**
     * 业务实现类对象，用来调用具体的业务方法
     */
    private final Object target;

    /**
     * 通过构造函数传入目标对象
     *
     * @param target target
     */
    public InvocationHandlerImpl(Object target) {
        this.target = target;
    }

    /**
     * 动态代理实际运行的代理方法
     *
     * @param proxy  proxy
     * @param method method
     * @param args   args
     * @return Object
     * @throws Throwable Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用开始处理");
        //下面invoke()方法是以反射的方式来创建对象，第一个参数是要创建的对象，第二个是构成方 法的参数，由第二个参数来决定创建对象使用哪个构造方法
        Object result = method.invoke(target, args);
        System.out.println("调用结束处理");
        return result;

    }
}
