package com.san.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.utils
 * @className SpringContextUtil
 * @description
 * @date 2019/05/19 21:21
 */
public final class SpringContextUtil implements ApplicationContextAware, DisposableBean {

    /**
     * applicationContext.
     */
    private static ApplicationContext applicationContext;

    /**
     * 不可实例化.
     */
    private SpringContextUtil() {
    }

    /**
     * @param applicationContext
     */
    public static void initApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * (non-Javadoc).
     *
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(
     *org.springframework.context.ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtil.initApplicationContext(applicationContext);
    }

    /**
     * (non-Javadoc).
     *
     * @see org.springframework.beans.factory.DisposableBean#destroy()
     */
    @Override
    public void destroy() {
    }

    /**
     * 获取applicationContext.
     *
     * @return applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取实例.
     *
     * @param name Bean名称
     * @return 实例
     */
    public static Object getBean(String name) {
        Assert.hasText(name);
        return applicationContext.getBean(name);
    }

    /**
     * 获取实例.
     *
     * @param name Bean名称
     * @param type Bean类型
     * @return 实例
     */
    public static <T> T getBean(String name, Class<T> type) {
        Assert.hasText(name);
        Assert.notNull(type);
        return applicationContext == null ? null : applicationContext.getBean(name, type);
    }

    /**
     * 获取国际化消息.
     *
     * @param code 代码
     * @param args 参数
     * @return 国际化消息
     */
    public static String getMessage(String code, Object... args) {
        if (StringUtils.isNotBlank(code)) {
            LocaleResolver localeResolver = applicationContext.getBean(LocaleResolver.class);
            if (localeResolver == null) {
                return null;
            }
            Locale locale = LocaleContextHolder.getLocale();
            try {
                return applicationContext.getMessage(code, args, locale);
            } catch (NoSuchMessageException e) {
                return code;
            }
        }
        return code;
    }

}


