package com.soft;

import cn.hutool.core.date.DateUtil;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;

/**
 * 描述: RedissonTest
 *
 * @author yujie
 * @date 2022/2/22 9:39
 */

public class RedissonTest {


    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://101.37.254.133:6379").setPassword("987654321lgr+redis.");
        RedissonClient redissonClient = Redisson.create(config);

        RBloomFilter(redissonClient);
//        RLongDouble(redissonClient);
//        RRateLimiterTest(redissonClient);
    }

    /**
     * 6.8.
     * 布隆过滤器（Bloom Filter）
     */
    public static void RBloomFilter(RedissonClient redissonClient) {

    }

    /**
     * 6.10 6.11
     * 描述: RRateLimiterTest
     * 累加器
     * 基于Redis的Redisson分布式整长型累加器（LongAdder）采用了与java.util.concurrent.atomic.LongAdder类似的接口。
     * 通过利用客户端内置的LongAdder对象，为分布式环境下递增和递减操作提供了很高得性能。
     * 据统计其性能最高比分布式AtomicLong对象快 12000 倍。完美适用于分布式统计计量场景。
     */
    public static void RLongDouble(RedissonClient redissonClient) {
        RDoubleAdder doubleAdder = redissonClient.getDoubleAdder("myDoubleAdder");
        doubleAdder.add(55);
        doubleAdder.increment();
        doubleAdder.decrement();
        System.out.println(doubleAdder.sum());

        RLongAdder longAdder = redissonClient.getLongAdder("myLongAdder");
        longAdder.add(88);
        longAdder.increment();
        longAdder.decrement();
        System.out.println(longAdder.sum());
    }

    /**
     * 6.12
     * 描述: RRateLimiterTest
     * 限流器
     * 基于Redis的分布式限流器（RateLimiter）可以用来在分布式环境下现在请求方的调用频率
     */
    public static void RRateLimiterTest(RedissonClient redissonClient) {
        RRateLimiter rateLimiter = redissonClient.getRateLimiter("myRateLimiter11111");
        // 初始化// 最大流速 = 每2秒钟产生1个令牌
        rateLimiter.trySetRate(RateType.OVERALL, 1, 2, RateIntervalUnit.SECONDS);
        while (true) {
            rateLimiter.acquire(1);
            System.out.println(DateUtil.current());
        }

    }
}
