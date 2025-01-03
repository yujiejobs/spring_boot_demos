package com.soft;

import cn.hutool.core.lang.Console;

public class ThreadLocalTest {

    static ThreadLocal<Object> local = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            local.set("包子");
            Console.log("线程1：{}", local.get());
        }).start();

        Thread.sleep(3000);
        new Thread(() -> {
            Console.log("线程2：{}", local.get());
        }).start();

    }

}
