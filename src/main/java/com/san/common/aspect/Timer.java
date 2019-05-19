package com.san.common.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.common.aspect
 * @className Timer
 * @description 时间记录annotation
 * 标注需要记录时间消耗的方法
 * @date 2019/05/19 22:54
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Timer {
}
