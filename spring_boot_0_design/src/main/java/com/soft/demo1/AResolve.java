package com.soft.demo1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 描述: 策略模式 之 A
 *
 * @author yujie
 * @date 2022/6/1 13:49
 */
@Slf4j
@Component
public class AResolve implements IStrategy {

    @Override
    public ResolveEnum gainFileType() {
        return ResolveEnum.A_RESOLVE;
    }

    @Override
    public void handle(Object object) {
        log.info("A 类型解析文件，参数：{}", object);
        //A类型解析具体逻辑
    }
}

