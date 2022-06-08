package com.soft.design.demo1;


import com.soft.design.demo1.strategy.IStrategy;
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


    /**
     * 策略容器
     */
    private final Map<StrategyEnum, IStrategy> iStrategy = new ConcurrentHashMap<>();

    /**
     * 获取策略服务
     *
     * @param strategyEnum 策略枚举
     * @param objectParam 参数
     */
    public void use(StrategyEnum strategyEnum, Object objectParam) {
        // 获取需要的策略服务
        IStrategy iStrategy = this.iStrategy.get(strategyEnum);
        if (iStrategy != null) {
            iStrategy.handle(objectParam);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 将已存在的对象放入策略容器中
        Map<String, IStrategy> beansOfType = applicationContext.getBeansOfType(IStrategy.class);
        beansOfType.values().forEach(strategyService -> iStrategy.put(strategyService.getStrategy(), strategyService));
    }
}
