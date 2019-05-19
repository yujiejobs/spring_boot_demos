package com.san.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.common.aspect
 * @className LogAspect
 * @description 全局方法执行时间
 * @date 2019/05/19 22:17
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    private final static long MAXTIMEMILLIS = 1000L;


    @Around("execution(* com.san.controller.*.*(..))")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long end = System.currentTimeMillis() - start;
            log.info("方法：{},耗时：{}", joinPoint, end);
            if (end > MAXTIMEMILLIS) {
                log.error("方法：{},耗时：{},请及时优化该方法", joinPoint, end);
            }
            return result;
        } catch (Throwable e) {
            long end = System.currentTimeMillis() - start;
            log.error("方法：{},耗时：{},exception:{}", joinPoint, end, e.getMessage());
            throw e;
        }
    }

}
