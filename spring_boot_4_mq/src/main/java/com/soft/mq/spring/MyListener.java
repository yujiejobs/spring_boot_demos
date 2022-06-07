package com.soft.mq.spring;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * 描述: MyListener
 *
 * @author yujie
 * @date 2022/4/27 9:22
 */
@Slf4j
@Component
@EnableAsync
public class MyListener implements ApplicationListener<MyEvent> {

    @Override
    @Async
    public void onApplicationEvent(MyEvent event) {
        log.info(StrUtil.format("MyListener监听消息:{}", JSONUtil.toJsonStr(event)));
    }

}
