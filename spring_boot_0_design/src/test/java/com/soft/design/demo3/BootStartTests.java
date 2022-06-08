package com.soft.design.demo3;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
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

    @Test
    void demo3() {
        TemplateAServiceImpl aService = new TemplateAServiceImpl();
        aService.handler("aService");
        aService.step();

        TemplateBServiceImpl bService = new TemplateBServiceImpl();
        bService.handler("bService");
        bService.step();
    }


}
