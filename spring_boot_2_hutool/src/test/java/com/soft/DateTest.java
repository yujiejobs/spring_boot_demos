package com.soft;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
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

    @Test
    void contextLoads() {
        Date now = new Date();
        String endOfDayStr = DateUtil.format(now, DatePattern.NORM_DATETIME_PATTERN);
        DateTime endOfDay = DateUtil.endOfDay(now);
        log.info(String.valueOf(endOfDay.after(DateUtil.parse(endOfDayStr))));
    }

}
