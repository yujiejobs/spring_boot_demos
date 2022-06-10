package com.soft.design.demo2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 描述: 责任链模式案例
 *
 * @author yujie
 * @date 2022/6/1 13:57
 */
@Slf4j
@SpringBootTest
class BootStartTests {

    @Autowired
    private ChainPatternDefault chainPatternDefault;

    @Test
    void demo2() {
        chainPatternDefault.execDefault("request", "response");
    }

}
