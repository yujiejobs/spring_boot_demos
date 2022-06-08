package com.soft.design.demo2.handler;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 描述: FilterHandlerB
 *
 * @author yujie
 * @date 2022/6/7 16:19
 */
@Component
@Order(2)
public class BFilterHandler extends AbstractHandler {

    @Override
    public void doFilter(String request, String response) {
        System.out.println("BFilterHandler");
    }
}
