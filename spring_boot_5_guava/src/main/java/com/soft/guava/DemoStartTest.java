package com.soft.guava;

/**
 * 描述: EventBusDemoTest
 *
 * @author yujie
 * @date 2022/2/18 16:24
 */
public class DemoStartTest {

    public static void main(String[] args) {
        EventBusCenter.register(new EventListener1());
        EventBusCenter.register(new EventListener3());
        EventBusCenter.register(new EventListener2());
        EventBusCenter.post(new NotifyEvent("13372817283", "123@qq.com", "666"));
    }
}

