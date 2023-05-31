package com.soft;

import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 描述: SpringBoot初始脚手架
 *
 * @author yujie
 * @date 2022/1/5 20:01
 */
@SpringBootApplication
@RestController
public class Start14 {

    public static void main(String[] args) {
        SpringApplication.run(Start14.class, args);
    }

    @Autowired
    RedissonClient redissonClient;

    /**
     * 分布式锁
     *
     * @return String
     */
    @ResponseBody
    @GetMapping("test-lock")
    public String testLock() {
        // 1.获取锁，只要锁的名字一样，获取到的锁就是同一把锁。
        RLock lock = redissonClient.getLock("lock");
        // 2.加锁
        lock.lock();
        try {
            System.out.println("加锁成功，执行后续代码。线程 ID：" + Thread.currentThread().getId());
            Thread.sleep(10000);
        } catch (Exception e) {
            //TODO
        } finally {
            lock.unlock();
            // 3.解锁
            System.out.println("Finally，释放锁成功。线程 ID：" + Thread.currentThread().getId());
        }

        return "test lock ok";
    }


    /**
     * 分布式读写锁
     * 写锁是一个拍他锁（互斥锁），读锁是一个共享锁。
     * <p>
     * 读锁 + 读锁：相当于没加锁，可以并发读。
     * 读锁 + 写锁：写锁需要等待读锁释放锁。
     * 写锁 + 写锁：互斥，需要等待对方的锁释放。
     * 写锁 + 读锁：读锁需要等待写锁释放。
     *
     * @return String
     */
    @ResponseBody
    @GetMapping("RReadWriteLock ")
    public String redissonClient() throws InterruptedException {
        RReadWriteLock rwlock = redissonClient.getReadWriteLock("anyRWLock");
        // 10秒钟以后自动解锁
        // 无需调用unlock方法手动解锁
        rwlock.readLock().lock(10, TimeUnit.SECONDS);
        // 或
        rwlock.writeLock().lock(10, TimeUnit.SECONDS);
        return "RReadWriteLock";
    }


    /**
     * 分布式信号量
     * 停车，占用停车位
     */
    @ResponseBody
    @RequestMapping("park")
    public String park() throws InterruptedException {
        // 获取信号量（停车场）
        RSemaphore park = redissonClient.getSemaphore("park");
        // 获取一个信号（停车位）
        park.acquire();
        return "OK";
    }

    /**
     * 释放车位
     * 分布式信号量
     * 总共 3 个车位
     */
    @ResponseBody
    @RequestMapping("leave")
    public String leave() throws InterruptedException {
        // 获取信号量（停车场）
        RSemaphore park = redissonClient.getSemaphore("park");
        // 释放一个信号（停车位）
        park.release();
        return "OK";
    }


}
