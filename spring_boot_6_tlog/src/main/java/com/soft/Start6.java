package com.soft;

import cn.hutool.core.thread.ThreadUtil;
import com.yomahub.tlog.core.annotation.TLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述: Start6
 *
 * @author yujie
 * @date 2022/1/5 20:01
 */
@SpringBootApplication
@Slf4j
@RestController
public class Start6 {

    public static void main(String[] args) {
        SpringApplication.run(Start6.class, args);
    }

    /**
     * 普通链路测试
     */
    @GetMapping("log")
    public void test() {
        log.info("这是接口进来打出来的日志！ ");
    }


    @TLogAspect({"id"})
    @GetMapping("log-id")
    public void demo1(String id){
        log.info("这是第一条日志");
        log.info("这是第二条日志");
        log.info("这是第三条日志");
        ThreadUtil.execute(() -> log.info("这是异步日志"));
    }

}
