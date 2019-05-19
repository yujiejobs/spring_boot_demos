package com.san.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.common.aspect
 * @className TimeAspect
 * @description
 * @date 2019/05/19 22:55
 */
@Aspect
@Component
@Slf4j
public class TimeAspect {

    /**
     * 修正Timer注解的全局唯一限定符
     */
    @Pointcut("@annotation(com.san.common.aspect.Timer)")
    private void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取目标类名称
        String clazzName = joinPoint.getTarget().getClass().getName();
        // 获取目标类方法名称
        String methodName = joinPoint.getSignature().getName();
        long start = System.currentTimeMillis();
        log.info("{}: {}: 方法开始...", clazzName, methodName);
        // 调用目标方法
        Object result = joinPoint.proceed();
        long time = System.currentTimeMillis() - start;
        log.info("{}: {}: : 方法结束... 花费时间: {} ms", clazzName, methodName, time);
        return result;
    }
    /**
     * @AspectJ的详细用法
     * 在spring AOP中目前只有执行方法这一个连接点，Spring AOP支持的AspectJ切入点指示符如下：
     *
     * 一些常见的切入点的例子
     * execution(public * * (. .)) 任意公共方法被执行时，执行切入点函数。
     * execution( * set* (. .)) 任何以一个“set”开始的方法被执行时，执行切入点函数。
     * execution( * com.demo.service.AccountService.* (. .)) 当接口AccountService 中的任意方法被执行时，执行切入点函数。
     * execution( * com.demo.service.. (. .)) 当service 包中的任意方法被执行时，执行切入点函数。 within(com.demo.service.) 在service 包里的任意连接点。 within(com.demo.service. .) 在service 包或子包的任意连接点。
     * this(com.demo.service.AccountService) 实现了AccountService 接口的代理对象的任意连接点。
     * target(com.demo.service.AccountService) 实现了AccountService 接口的目标对象的任意连接点。
     * args(Java.io.Serializable) 任何一个只接受一个参数，且在运行时传入参数实现了 Serializable 接口的连接点
     * 增强的方式：
     * @Before：方法前执行
     * @AfterReturning：运行方法后执行
     * @AfterThrowing：Throw后执行
     * @After：无论方法以何种方式结束，都会执行（类似于finally）
     * @Around：环绕执行
     */
}