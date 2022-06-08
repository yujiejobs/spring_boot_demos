package com.soft.design.demo1;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 描述: 策略模式案例
 *
 * @author yujie
 * @date 2022/6/1 13:57
 */
@Slf4j
@SpringBootTest
class BootStartTests {

    @Autowired
    private StrategyUseService strategyUseService;

    @Test
    void demo1() {
        strategyUseService.use(StrategyEnum.B_RESOLVE, 123456);
    }
}
