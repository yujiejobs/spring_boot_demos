package com.soft.guava;

import lombok.Data;

/**
 * 描述: 通知事件类
 *
 * @author yujie
 * @date 2022/2/18 16:24
 */
@Data
public class NotifyEvent {

    private String mobileNo;

    private String emailNo;

    private String imNo;

    public NotifyEvent(String mobileNo, String emailNo, String imNo) {
        this.mobileNo = mobileNo;
        this.emailNo = emailNo;
        this.imNo = imNo;
    }
}
