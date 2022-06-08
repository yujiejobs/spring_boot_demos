package com.soft;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
@Slf4j
public class ThreadTest {

    @Test
    void test0() {
        AtomicInteger index = new AtomicInteger();
        TimeInterval timer = DateUtil.timer();
        for (int i = 0; i < 6000; i++) {
            int andIncrement = index.getAndIncrement();
            excTask();
        }
        System.out.println("执行时间" + timer.intervalSecond());

    }

    @Test
    void test1() {
        AtomicInteger index = new AtomicInteger();
        TimeInterval timer = DateUtil.timer();
        for (int i = 0; i < 50000; i++) {
            ThreadUtil.execute(() -> {
                int andIncrement = index.getAndIncrement();
                excTask();
            });
        }
        System.out.println("执行时间" + timer.interval());
        // 执行时间1198 执行时间18992

    }

    private void excTask() {
        ThreadUtil.sleep(100);
    }

}
