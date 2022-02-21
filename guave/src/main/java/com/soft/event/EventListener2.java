package com.soft.event;

import com.google.common.eventbus.Subscribe;

/**
 * 描述: 观察者2
 *
 * @author yujie
 * @date 2022/2/18 16:24
 */
public class EventListener2 {

    /**
     * 加了订阅，这里标记这个方法是事件处理方法
     *
     * @param notifyEvent notifyEvent
     */
    @Subscribe
    public void handle(NotifyEvent notifyEvent) {
        System.out.println("观察者2：发送Email消息" + notifyEvent.getEmailNo());
    }


}
