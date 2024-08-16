package com.soft.guava.event;

import com.google.common.eventbus.Subscribe;

/**
 * 描述: 观察者3
 *
 * @author yujie
 * @date 2022/2/18 16:24
 */
public class EventListener3 {

    /**
     * 加了订阅，这里标记这个方法是事件处理方法
     *
     * @param notifyEvent notifyEvent
     */
    @Subscribe
    public void handle(NotifyEvent notifyEvent) {
        System.out.println("观察者3：发送IM消息" + notifyEvent.getImNo());
        // 模拟方法执行异常
        throw new RuntimeException("发送失败！");
    }


}
