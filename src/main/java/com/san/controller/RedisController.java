package com.san.controller;

import com.san.domain.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.controller
 * @className RedisController
 * @description
 * @date 2019/05/13 22:37
 */
@RequestMapping("redis")
@RestController
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/setStringValue")
    public void setValue() {
        stringRedisTemplate.opsForValue().set("key", "String For Value");
    }

    @RequestMapping("/getStringValue")
    public void getValue() {
        System.out.println(stringRedisTemplate.opsForValue().get("key"));
    }

    @RequestMapping("/setObjectValue")
    public void setObjectValue() {
        UserVO vo = new UserVO(1L, "username", "2211024378@qq.com");
        ValueOperations<String, UserVO> operations = redisTemplate.opsForValue();
        operations.set("time_not_Expired", vo);
        operations.set("time_1_Expired", vo, 1, TimeUnit.SECONDS);
    }

    @RequestMapping("/getObjectValue")
    public void getObjectValue() {
        System.out.println(redisTemplate.opsForValue().get("time_not_Expired"));
        System.out.println(redisTemplate.opsForValue().get("time_1_Expired"));
    }

    /**
     * 自动使用缓存,其中 value 的值就是缓存到 Redis 中的 key
     * @return
     */
    @RequestMapping("/getUser")
    @Cacheable(value="user-key2")
    public UserVO setUser() {
        UserVO vo = new UserVO(2L, "username", "33333333@qq.com");
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return vo;
    }

}
