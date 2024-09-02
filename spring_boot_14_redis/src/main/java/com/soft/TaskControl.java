package com.soft;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TaskControl {

    @Autowired
    private StringRedisTemplate redisTemplate;
    static String key = "task";

    @ResponseBody
    @GetMapping("put")
    public String put(String msg, int score) {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.add(key, msg, score);
        return "success";
    }


    @ResponseBody
    @RequestMapping("batch")
    public String batch(int size) {
        for (int i = 0; i < size; i++) {
            ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
            int v;
            if (size > 50) {
                v = RandomUtil.getRandom().nextInt(1, 1000);
            } else {
                v = RandomUtil.getRandom().nextInt(10000, 20000);
            }
            zSetOperations.add(key, String.valueOf(v), i);
        }
        return "success";
    }

    /**
     * 启动消费线程
     */
    @GetMapping("startConsume")
    public void startConsume() throws InterruptedException {
        log.info("启动消费者");
        while (true) {
            try {
                // 每xx秒消费一次
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();

            ZSetOperations.TypedTuple<String> poppedMax = zSetOperations.popMax(key);
            if (poppedMax != null) {
                String value = poppedMax.getValue();
                Double score = poppedMax.getScore();
                log.info(StrUtil.format("{}出队 value:{} score:{}", Thread.currentThread().getId(), value, score));
                log.info("堆积数量：{}", zSetOperations.size(key));
            } else {
                log.info("未获取到数据,停止3秒");
                Thread.sleep(3000);
            }

        }

    }
}
