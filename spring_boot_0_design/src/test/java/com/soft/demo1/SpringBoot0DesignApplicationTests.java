package com.soft.demo1;

import com.soft.demo2.ChainPatternDefault;
import com.soft.demo3.TemplateAServiceImpl;
import com.soft.demo3.TemplateBServiceImpl;
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
class SpringBoot0DesignApplicationTests {

    @Autowired
    private StrategyUseService strategyUseService;

    @Test
    void demo1() {
        strategyUseService.use(ResolveEnum.B_RESOLVE,123456);
    }

    @Autowired
    private ChainPatternDefault chainPatternDefault;

    @Test
    void demo2() {
        log.info("默认顺序");
        chainPatternDefault.execDefault("request","response");
    }

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
