package com.soft.demo2;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 描述: 安全校验对象
 *
 * @author yujie
 * @date 2022/6/1 14:07
 */
@Component
@Order(2)
public class CheckSecurityFilterObject extends AbstractHandler {

    @Override
    public void doFilter(String request, String response) {
        System.out.println("安全校验对象");
    }
}
