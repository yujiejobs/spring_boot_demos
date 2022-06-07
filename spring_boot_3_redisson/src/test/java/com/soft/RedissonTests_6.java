package com.soft;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 6. 分布式对象
 * https://github.com/redisson/redisson/wiki/6.-%E5%88%86%E5%B8%83%E5%BC%8F%E5%AF%B9%E8%B1%A1
 */
@SpringBootTest
class RedissonTests_6 {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 6.7. 话题（订阅分发）
     * https://github.com/redisson/redisson/wiki/6.-%E5%88%86%E5%B8%83%E5%BC%8F%E5%AF%B9%E8%B1%A1#67-%E8%AF%9D%E9%A2%98%E8%AE%A2%E9%98%85%E5%88%86%E5%8F%91
     */
    @Test
    void rTopic_6_7() {
        RTopic topic = redissonClient.getTopic("anyTopic");
        topic.addListener(DemoObject.class, (channel, msg) -> System.out.println(StrUtil.format("收到订阅消息：{}", JSONUtil.toJsonStr(msg))));
        threadNoEnd();
    }

    @Test
    void publish_6_7() {
        for (int i = 0; i < 100; i++) {
            RTopic topic = redissonClient.getTopic("anyTopic");
            String msg = "话题（订阅分发）消息内容......" + i;
            DemoObject someObject = new DemoObject(msg);
            topic.publish(someObject);
            System.out.println(StrUtil.format("发布订阅消息：{}", msg));
        }
    }

    /**
     * 6.12. 限流器（RateLimiter）
     * https://github.com/redisson/redisson/wiki/6.-%E5%88%86%E5%B8%83%E5%BC%8F%E5%AF%B9%E8%B1%A1#612-%E9%99%90%E6%B5%81%E5%99%A8ratelimiter
     */
    @Test
    void rateLimiter_6_12() {
        // 1.开启j个线程
        for (int j = 0; j < 2; j++) {
            ThreadUtil.execAsync(() -> {
                System.out.println(StrUtil.format("线程启动 【{}】", Thread.currentThread().getName()));
                RRateLimiter rateLimiter = redissonClient.getRateLimiter("rateLimiter");
                // 2.最大流速 = 每1秒钟产生x个令牌
                rateLimiter.trySetRate(RateType.OVERALL, 1, 1, RateIntervalUnit.SECONDS);
                for (int i = 0; i < 50; i++) {
                    // 3.每次获取并消耗1个令牌
                    rateLimiter.acquire(1);
                    System.out.println(StrUtil.format("线程执行 【{}】 : 【{}】", Thread.currentThread().getName(), i));
                }
            });
        }
        threadNoEnd();

    }

    /**
     * 保持线程不停止
     */
    private void threadNoEnd() {
        System.out.println("保持主线程不停止");
        while (true) {

        }
    }

}
