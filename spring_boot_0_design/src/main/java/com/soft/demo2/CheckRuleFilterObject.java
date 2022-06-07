package com.soft.demo2;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 描述: 规则拦截
 *
 * @author yujie
 * @date 2022/6/1 14:06
 */
@Component
@Order(4)
public class CheckRuleFilterObject extends AbstractHandler {

    @Override
    public void doFilter(String request, String response) {
        System.out.println("规则拦截");
    }
}