package com.soft.event;

import com.google.common.eventbus.EventBus;

/**
 * 描述: EventBusCenter
 *
 * @author yujie
 * @date 2022/2/18 16:21
 */
public class EventBusCenter {

    private static final EventBus eventBus = new EventBus();

    private EventBusCenter() {
    }

    public static EventBus getInstance() {
        return eventBus;
    }

    /**
     * 添加观察者
     * @param obj
     */
    public static void register(Object obj) {
        eventBus.register(obj);
    }

    /**
     * 移除观察者
     * @param obj
     */
    public static void unregister(Object obj) {
        eventBus.unregister(obj);
    }

    /**
     * 把消息推给观察者
     * @param obj
     */
    public static void post(Object obj) {
        eventBus.post(obj);
    }
}

