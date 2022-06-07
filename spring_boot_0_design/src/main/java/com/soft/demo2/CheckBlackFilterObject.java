package com.soft.demo2;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 描述: 黑名单校验对象
 *
 * @author yujie
 * @date 2022/6/1 14:04
 */
@Component
@Order(3)
public class CheckBlackFilterObject extends AbstractHandler {

    @Override
    public void doFilter(String request, String response) {
        System.out.println("黑名单校验对象");
    }

}