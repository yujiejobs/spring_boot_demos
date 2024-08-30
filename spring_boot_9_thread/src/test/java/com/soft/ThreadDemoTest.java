package com.soft;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadDemoTest {


    public static void main(String[] args) throws InterruptedException {
        int size = 20;
        TimeInterval timer = DateUtil.timer();
        CountDownLatch countDownLatch = new CountDownLatch(size);
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < size; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getId());
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("执行耗时（秒）：" + timer.intervalSecond());

    }

}
