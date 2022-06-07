package com.soft.demo1;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述: 策略模式服务
 *
 * @author yujie
 * @date 2022/6/1 13:51
 */
@Component
public class StrategyUseService implements ApplicationContextAware {


    private final Map<ResolveEnum, IStrategy> iStrategy = new ConcurrentHashMap<>();

    public void use(ResolveEnum resolveEnum, Object objectParam) {
        IStrategy iStrategy = this.iStrategy.get(resolveEnum);
        if (iStrategy != null) {
            iStrategy.handle(objectParam);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, IStrategy> beansOfType = applicationContext.getBeansOfType(IStrategy.class);
        beansOfType.values().forEach(strategyService -> iStrategy.put(strategyService.gainFileType(), strategyService));
    }
}
