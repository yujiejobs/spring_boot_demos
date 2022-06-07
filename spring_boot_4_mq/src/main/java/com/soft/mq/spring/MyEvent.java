package com.soft.mq.spring;

import org.springframework.context.ApplicationEvent;

import java.io.Serializable;

/**
 * 描述: MyEvent
 *
 * @author yujie
 * @date 2022/4/27 9:19
 */
public class MyEvent extends ApplicationEvent implements Serializable {

    /**
     * 用户名
     */
    private String userName;

    public MyEvent(Object source) {
        super(source);
    }

    public MyEvent(Object source, String userName) {
        super(source);
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
