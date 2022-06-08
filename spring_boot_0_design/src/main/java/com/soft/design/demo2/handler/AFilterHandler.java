package com.soft.design.demo2.handler;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 描述: AFilterHandler
 *
 * @author yujie
 * @date 2022/6/1 14:04
 */
@Component
@Order(1)
public class AFilterHandler extends AbstractHandler {

    @Override
    public void doFilter(String request, String response) {
        System.out.println("AFilterHandler");
    }
}
