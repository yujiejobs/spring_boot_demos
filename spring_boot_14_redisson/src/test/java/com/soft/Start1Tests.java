package com.soft;

import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Start1Tests {

    @Autowired
    RedissonClient redissonClient;

    @Test
    void contextLoads() {
        System.out.println(redissonClient);
        RLock lock = redissonClient.getLock("anyLock");
        // 最常见的使用方法
        lock.lock();
    }

}
