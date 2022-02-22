package som.soft.time;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;

import java.util.Random;

/**
 * 描述: TimeTest
 *
 * @author yujie
 * @date 2022/2/18 16:37
 */
public class TimeTest {

    public static void main(String[] args) throws InterruptedException {
        TimeInterval timer = DateUtil.timer();
        Thread.sleep(new Random().nextInt(1000));
        System.out.println("执行时间 ：" + timer.intervalSecond());
        System.out.println("执行时间：" + timer.intervalSecond());
        System.out.println("执行时间：" + timer.intervalSecond());
        System.out.println("执行时间：" + timer.intervalSecond());

    }

}
