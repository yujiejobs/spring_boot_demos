package com.soft.design.demo3;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 描述: 模板方法模式 案例
 * 模板方法模式：定义一个操作中的算法骨架（父类），而将一些步骤延迟到子类中。
 * 模板方法使得子类可以不改变一个算法的结构来重定义该算法的
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
