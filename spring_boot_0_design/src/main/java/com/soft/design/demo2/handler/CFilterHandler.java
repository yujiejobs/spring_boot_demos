package com.soft.design.demo2.handler;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 描述: FilterC
 *
 * @author yujie
 * @date 2022/6/7 16:18
 */
@Component
@Order(3)
public class CFilterHandler extends AbstractHandler {

    @Override
    public void doFilter(String request, String response) {
        System.out.println("C");
    }

}