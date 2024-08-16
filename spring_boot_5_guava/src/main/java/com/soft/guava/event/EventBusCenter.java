package com.soft.guava.event;

import com.google.common.eventbus.EventBus;

/**
 * 描述: EventBusCenter
 *
 * @author yujie
 * @date 2022/2/18 16:21
 */
public class EventBusCenter {

    private static final EventBus EVENT_BUS = new EventBus();

    private EventBusCenter() {
    }

    public static EventBus getInstance() {
        return EVENT_BUS;
    }

    /**
     * 添加观察者
     *
     * @param obj obj
     */
    public static void register(Object obj) {
        EVENT_BUS.register(obj);
    }

    /**
     * 移除观察者
     *
     * @param obj obj
     */
    public static void unregister(Object obj) {
        EVENT_BUS.unregister(obj);
    }

    /**
     * 把消息推给观察者
     *
     * @param obj obj
     */
    public static void post(Object obj) {
        EVENT_BUS.post(obj);
    }
}

