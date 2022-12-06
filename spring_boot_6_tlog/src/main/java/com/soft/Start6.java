package com.soft;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.IdUtil;
import com.yomahub.tlog.core.thread.TLogInheritableTask;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

    @GetMapping("log-id")
    public void demo1() {
        String id = IdUtil.fastSimpleUUID();
        log.info("这是同步第一条日志 [{}]", id);
        ThreadUtil.newThread(new TLogInheritableTask() {
            @Override
            public void runTask() {
                log.info("这是异步日志 [{}]！！！！", id);
            }
        }, "TLogInheritableTask-Name").start();
        ThreadUtil.sleep(1000);
        log.info("这是同步第二条日志 [{}]", id);
        log.info("这是同步第三条日志 [{}]", id);

    }

}
