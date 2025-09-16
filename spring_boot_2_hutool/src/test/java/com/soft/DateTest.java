package com.soft;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * 描述: DateTest
 *
 * @author yujie
 * @date 2022/1/5 20:35
 */
@SpringBootTest
@Slf4j
public class DateTest {

    /**
     * 计时器
     */
    @Test
    void calcMethodTime() throws InterruptedException {
        TimeInterval timer = DateUtil.timer();
        //---------------------------------
        //-------这是执行过程
        //---------------------------------
        ThreadUtil.sleep(800);

        log.info("花费毫秒数 {}", timer.interval());
        log.info("返回花费时间，并重置开始时间 {}", timer.intervalRestart());
        log.info("花费分钟数 {}", timer.intervalMinute());
    }

    /**
     * 分组计时器
     */
    @Test
    void calcMethodTimeGroup() {
        final TimeInterval timer = new TimeInterval();
        // 分组1
        timer.start("1");
        ThreadUtil.sleep(800);
        // 分组2
        timer.start("2");
        ThreadUtil.sleep(900);

        log.info("Timer 1 took {} ms", timer.intervalMs("1"));
        log.info("Timer 2 took {} ms", timer.intervalMs("2"));
    }


    /**
     * 日期格式转换
     * https://www.hutool.cn/docs/#/core/%E6%97%A5%E6%9C%9F%E6%97%B6%E9%97%B4/%E6%97%A5%E6%9C%9F%E6%97%B6%E9%97%B4%E5%B7%A5%E5%85%B7-DateUtil
     */
    @Test
    void dataFormat() {
        String dateStr = "2017-03-01";
        Date date = DateUtil.parse(dateStr);
        log.info("0:" + date.toString());

        String dateStr1 = "2017-03-01";
        Date date1 = DateUtil.parse(dateStr1, "yyyy-MM-dd");
        log.info("1:" + date1.toString());

        String dateStr2 = "2017-03-01 23:59:50";
        Date date2 = DateUtil.parse(dateStr2);
        log.info("2:" + date2.toString());

        String dateStr3 = "2022年6月08日 23时06分5秒";
        Date date3 = DateUtil.parse(dateStr3);
        log.info("3:" + date3.toString());


        Date date4 = DateUtil.parse("20250916105701");
        log.info("4:" + date4.toString());

    }
}