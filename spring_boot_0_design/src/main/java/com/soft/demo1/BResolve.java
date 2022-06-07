package com.soft.demo1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 描述: 策略模式 之 B
 *
 * @author yujie
 * @date 2022/6/1 13:50
 */
@Slf4j
@Component
public class BResolve implements IStrategy {

    @Override
    public ResolveEnum gainFileType() {
        return ResolveEnum.B_RESOLVE;
    }


    @Override
    public void handle(Object object) {
        log.info("B 类型解析文件，参数：{}", object);
        //B类型解析具体逻辑
    }
}
