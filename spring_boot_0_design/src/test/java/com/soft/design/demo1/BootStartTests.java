package com.soft.design.demo1;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 描述: 策略模式案例
 * 定义了一系列的算法 或 逻辑 或 相同意义的操作，并将每一个算法、逻辑、操作封装起来，而且使它们还可以相互替换。
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
