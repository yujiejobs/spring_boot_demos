package com.soft.latch;

import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * CountDownLatch 表示计数器，可以给 CountDownLatch 设置一个数字，一个线程调用 CountDownLatch 的 await() 将会阻塞
 * 其他线程可以调用 CountDownLatch 的 countDown() 方法来对 CountDownLatch 中的数字减一，当数字被减成0后，所有 await 的线程都将被唤醒
 * 对应的底层原理就是，调用 await() 方法的线程会利用 AQS 排队，一旦数字被减为 0，则会将AQS中排队的线程依次唤醒。
 */
public class LatchTest {
    private static final CountDownLatch countDownLatch = new CountDownLatch(3);

    public static void main(String[] args) {
        ThreadUtil.execute(() -> {
            try {
                Console.log("线程1执行开始");
                countDownLatch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Console.log("线程1执行结束");
        });

        ThreadUtil.execute(() -> {
            try {
                Console.log("线程2执行开始");
                countDownLatch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Console.log("线程2执行结束");
        });

        countDownLatch.countDown();
        countDownLatch.countDown();
        countDownLatch.countDown();
        System.out.println("主线程结束");
        while (true){

        }
    }

}
