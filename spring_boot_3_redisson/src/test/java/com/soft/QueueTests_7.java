package com.soft;

import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RDeque;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

/**
 * 7. 分布式集合
 */
@SpringBootTest
public class QueueTests_7 {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 7.8. 队列（Queue）
     * https://github.com/redisson/redisson/wiki/7.-%E5%88%86%E5%B8%83%E5%BC%8F%E9%9B%86%E5%90%88#78-%E9%98%9F%E5%88%97queue
     */
    @Test
    void queue_7_8() {
        RQueue<DemoObject> queue = redissonClient.getQueue("anyQueue_7_8");
        for (int i = 0; i < 10; i++) {
            String msg = StrUtil.format("{} 队列信息......", i);
            queue.add(new DemoObject(msg));
        }
        // 查看首个元素，不会移除首个元素，如果队列是空的就抛出异常NoSuchElementException
        DemoObject element = queue.element();
        // 查看首个元素，不会移除首个元素，如果队列是空的就返回null
        DemoObject peek = queue.peek();
        // 将首个元素从队列中弹出，如果队列是空的，就返回null
        DemoObject poll = queue.poll();
    }

    /**
     * 双端队列（Deque）
     * https://github.com/redisson/redisson/wiki/7.-%E5%88%86%E5%B8%83%E5%BC%8F%E9%9B%86%E5%90%88#79-%E5%8F%8C%E7%AB%AF%E9%98%9F%E5%88%97deque
     */
    @Test
    void deque_7_9() {
        RDeque<DemoObject> queue = redissonClient.getDeque("anyDeque_7_9");
        queue.addFirst(new DemoObject("头部双端队列"));
        queue.addLast(new DemoObject("尾部双端队列"));
        DemoObject obj = queue.removeFirst();
        DemoObject someObj = queue.removeLast();
    }

    /**
     * 延迟队列（Delayed Queue）
     * https://github.com/redisson/redisson/wiki/7.-%E5%88%86%E5%B8%83%E5%BC%8F%E9%9B%86%E5%90%88#715-%E5%BB%B6%E8%BF%9F%E9%98%9F%E5%88%97delayed-queue
     */
    @Test
    void delayed_queue_7_15() {
        RQueue<String> queue = redissonClient.getQueue("delayedQueue_7_15");
        RDelayedQueue<String> delayedQueue = redissonClient.getDelayedQueue(queue);
        // 10秒钟以后将消息发送到指定队列
        delayedQueue.offer("msg1", 10, TimeUnit.SECONDS);
        // 一分钟以后将消息发送到指定队列
        delayedQueue.offer("msg2", 1, TimeUnit.MINUTES);
        while (true) {
            String poll = queue.poll();
            if (StrUtil.isNotBlank(poll)) {
                System.out.println(poll);
            }

        }

    }

}
