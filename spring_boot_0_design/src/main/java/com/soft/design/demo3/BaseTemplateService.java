package com.soft.design.demo3;

import lombok.extern.slf4j.Slf4j;

/**
 * 描述: 模板方法模式
 *
 * @author yujie
 * @date 2022/6/1 14:32
 */
@Slf4j
abstract class BaseTemplateService {

    /**
     * 模板方法处理流程
     *
     * @param req req
     * @return String
     */
    public String handler(String req) {
        step1();
        step2();
        step3();
        return "处理成功";
    }

    void step1() {
        log.info("step 1");
    }

    void step2() {
        log.info("step 2");
    }

    void step3() {
        log.info("step 3");
    }

    /**
     * 提供给子类实现
     *
     * @return 提供给子类实现
     */
    public abstract boolean step();

}
