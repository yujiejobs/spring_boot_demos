package com.soft.semaphore;

import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.Semaphore;

/**
 * Semaphore 表示信号量，可以设置许可的个数，表示同时允许最多多少个线程使用该信号量，通过 acquire（）来获取许可
 * 如果没有许可可用则线程阻塞，并通过AQS来排队，可以通过 release 方法来释放许可，当某个线程释放了某个许可后
 * 会从AQS中正在排队的第一个线程开始依次唤醒，直到没有空闲许可。
 *
 * @author yujie
 * @date 2022/7/13 20:38
 */
public class SemaphoreTest {

    private static final Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) {
        ThreadUtil.execute(() -> {
            try {
                Console.log("线程1执行开始");
                semaphore.acquire();
                ThreadUtil.sleep(2000);
                semaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Console.log("线程1执行结束");
        });

        ThreadUtil.execute(() -> {
            try {
                Console.log("线程2执行开始");
                semaphore.acquire();
                ThreadUtil.sleep(2000);
                semaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Console.log("线程2执行结束");
        });


        ThreadUtil.execute(() -> {
            try {
                Console.log("线程3执行开始");
                semaphore.acquire();
                ThreadUtil.sleep(2000);
                semaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Console.log("线程3执行结束");
        });


        System.out.println("主线程结束");
        while (true) {

        }
    }
}
