package com.san;

import com.san.utils.SpringContextUtil;
import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.security.auth.message.config.AuthConfigFactory;

@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableSwagger2
public class SpringBootInitApplication {

    public static void main(String[] args) {
        // 解决tomcat 版本太高，org.apache.catalina.authenticator.AuthenticatorBase.getJaspicProvider报错问题
        if (AuthConfigFactory.getFactory() == null) {
            AuthConfigFactory.setFactory(new AuthConfigFactoryImpl());
        }
        ApplicationContext run = SpringApplication.run(new Class[]{SpringBootInitApplication.class}, args);
        SpringContextUtil.initApplicationContext(run);
    }

}
