package com.san.controller;

import com.san.domain.vo.UserVO;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.controller
 * @className RabbitReceiverController
 * @description
 * @date 2019/05/13 23:36
 */
@Service
@RabbitListener(queues = "MyRabbitMqQueues")
public class RabbitReceiverServer {

    @RabbitHandler
    public void process1(String info) {
        System.out.println("Receiver  : " + info);
    }

    @RabbitHandler
    public void process2(UserVO info) {
        System.out.println("Receiver object : " + info.toString());
    }

}
