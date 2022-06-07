package com.soft.demo2;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 描述: 参数校验对象
 *
 * @author yujie
 * @date 2022/6/1 14:04
 */
@Component
@Order(1)
public class CheckParamFilterObject extends AbstractHandler {

    @Override
    public void doFilter(String request, String response) {
        System.out.println("参数校验对象");
    }
}
