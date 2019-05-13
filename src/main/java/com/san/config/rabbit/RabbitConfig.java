package com.san.config.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.config.rabbit
 * @className RabbitConfig
 * @description
 * @date 2019/05/13 23:33
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue Queue() {
        return new Queue("MyRabbitMqQueues");
    }

}
