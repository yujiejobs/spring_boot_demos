package com.san.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.task
 * @className SchedulerTask
 * @description 定时任务实现
 *  @Scheduled(fixedRate = 6000) ：上一次开始执行时间点之后6秒再执行
 *  @Scheduled(fixedDelay = 6000) ：上一次执行完毕时间点之后6秒再执行
 *  @Scheduled(initialDelay=1000, fixedRate=6000) ：第一次延迟1秒后执行，之后按 fixedRate 的规则每6秒执行一次
 * @date 2019/05/14 0:29
 */
@Component
public class SchedulerTask {

    private int count = 0;

    /**
     * 每隔6秒打印
     */
    @Scheduled(cron = "*/600 * * * * ?")
    private void process() {
        System.out.println("this is scheduler task runing  " + (count++));
    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 每隔6秒打印
     */
    @Scheduled(fixedRate = 600000)
    public void reportCurrentTime() {
        System.out.println("现在时间：" + dateFormat.format(new Date()));
    }


}