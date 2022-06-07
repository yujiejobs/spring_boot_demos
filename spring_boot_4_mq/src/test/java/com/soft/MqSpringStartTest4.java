package com.soft;

import com.soft.mq.spring.MyEvent;
import com.soft.mq.spring.MyPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 描述: MqSpringStartTest4
 *
 * @author yujie
 * @date 2022/4/29 9:28
 */
@SpringBootTest
class MqSpringStartTest4 {

    @Autowired
    private MyPublisher myPublisher;

    @Test
    void sendMsg1() {
        MyEvent myEvent = new MyEvent(this, "包子");
        myPublisher.sendMsg(myEvent);
    }

    @Test
    void sendMsg2() {
        MyEvent myEvent = new MyEvent(this, "馒头");
        myPublisher.sendMsg(myEvent);
    }


}
