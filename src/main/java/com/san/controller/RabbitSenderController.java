package com.san.controller;

import com.san.domain.vo.UserVO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.controller
 * @className RabbitSenderController
 * @description
 * @date 2019/05/13 23:34
 */
@RestController
public class RabbitSenderController {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @GetMapping("send")
    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("MyRabbitMqQueues", context);
    }

    @GetMapping("sendTen")
    public void sendTen() {
        for (int i = 0; i < 10; i++) {
            this.rabbitTemplate.convertAndSend("MyRabbitMqQueues", String.valueOf(i));
        }
    }

    @GetMapping("sendObject")
    public void sendObject() {
        UserVO vo = new UserVO(1L,"sannian","123456");
        System.out.println("Sender : " + vo.toString());
        this.rabbitTemplate.convertAndSend("MyRabbitMqQueues", vo);
    }

    /**
     * 发送send1会匹配到topic.#和topic.message 两个Receiver都可以收到消息
     */
    @GetMapping("send1")
    public void send1() {
        String context = "hi, i am message 1";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("exchange", "topic.message", context);
    }

    /**
     * 发送send2只有topic.#可以匹配所有只有Receiver2监听到消息
     */
    @GetMapping("send2")
    public void send2() {
        String context = "hi, i am messages 2";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("exchange", "topic.messages", context);
    }

    @GetMapping("sendAll")
    public void sendAll() {
        String context = "hi, fanout msg ";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("fanoutExchange","", context);
    }


}
