package com.san.config.session;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.config.session
 * @className SessionConfig
 * @description
 *  maxInactiveIntervalInSeconds: 设置 Session 失效时间，使用 Redis Session 之后，
 *  原 Spring Boot 的 server.session.timeout 属性不再生效。
 * @date 2019/05/13 23:14
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 10)
public class SessionConfig {
}