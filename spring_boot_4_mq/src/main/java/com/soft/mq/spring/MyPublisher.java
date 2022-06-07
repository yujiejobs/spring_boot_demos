package com.soft.mq.spring;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void sendMsg(ApplicationEvent myEvent) {
        applicationEventPublisher.publishEvent(myEvent);
        log.info(StrUtil.format("MyListener发送消息:{}", JSONUtil.toJsonStr(myEvent)));
    }
}
