package com.soft.pool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 五种线程池的分类和作用
 */
public class ThreadPoolTypeTests {


    /**
     * 作用：创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们，并在需要时使用提供的 ThreadFactory 创建新线程。
     * <p>
     * 特征：
     * （1）线程池中数量没有固定，可达到最大值（Interger. MAX_VALUE）
     * （2）线程池中的线程可进行缓存重复利用和回收（回收默认时间为1分钟）
     * （3）当线程池中，没有可用线程，会重新创建一个线程
     * <p>
     * 创建方式： Executors.newCachedThreadPool();
     */
    @Test
    void newCachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
    }

    /**
     * 作用：创建一个可重用固定线程数的线程池，以共享的无界队列方式来运行这些线程。
     * 在任意点，在大多数 nThreads 线程会处于处理任务的活动状态。
     * 如果在所有线程处于活动状态时提交附加任务，则在有可用线程之前，附加任务将在队列中等待。
     * 如果在关闭前的执行期间由于失败而导致任何线程终止，那么一个新线程将代替它执行后续的任务（如果需要）。
     * 在某个线程被显式地关闭之前，池中的线程将一直存在。
     * <p>
     * 特征：
     * （1）线程池中的线程处于一定的量，可以很好的控制线程的并发量
     * （2）线程可以重复被使用，在显示关闭之前，都将一直存在
     * （3）超出一定量的线程被提交时候需在队列中等待
     * <p>
     * 创建方式：
     * （1）Executors.newFixedThreadPool(int nThreads)；//nThreads为线程的数量
     * （2）Executors.newFixedThreadPool(int nThreads，ThreadFactory threadFactory)；//nThreads为线程的数量，threadFactory创建线程的工厂方式
     */
    @Test
    void newFixedThreadPool() {
        int nThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
    }

    /**
     * 作用：创建一个使用单个 worker 线程的 Executor，以无界队列方式来运行该线程。
     * （注意，如果因为在关闭前的执行期间出现失败而终止了此单个线程，那么如果需要，一个新线程将代替它执行后续的任务）。
     * 可保证顺序地执行各个任务，并且在任意给定的时间不会有多个线程是活动的。
     * 与其他等效的 newFixedThreadPool(1) 不同，可保证无需重新配置此方法所返回的执行程序即可使用其他的线程。
     * <p>
     * 特征：
     * （1）线程池中最多执行1个线程，之后提交的线程活动将会排在队列中以此执行
     * <p>
     * 创建方式：
     * （1）Executors.newSingleThreadExecutor() ；
     * （2）Executors.newSingleThreadExecutor(ThreadFactory threadFactory)；// threadFactory创建线程的工厂方式
     */
    @Test
    void newSingleThreadExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
    }

    /**
     * 作用： 创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行。
     * <p>
     * 特征：
     * （1）线程池中具有指定数量的线程，即便是空线程也将保留
     * （2）可定时或者延迟执行线程活动
     * <p>
     * 创建方式：
     * （1）Executors.newScheduledThreadPool(int corePoolSize)；// corePoolSize线程的个数
     * （2）newScheduledThreadPool(int corePoolSize, ThreadFactory threadFactory)；// corePoolSize线程的个数，threadFactory创建线程的工厂
     */
    @Test
    void newScheduledThreadPool() {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
    }


    /**
     * 作用： 创建一个单线程执行程序，它可安排在给定延迟后运行命令或者定期地执行。
     * <p>
     * 特征：
     * （1）线程池中最多执行1个线程，之后提交的线程活动将会排在队列中以此执行
     * （2）可定时或者延迟执行线程活动
     * <p>
     * 创建方式：
     * （1）Executors.newSingleThreadScheduledExecutor() ；
     * （2）Executors.newSingleThreadScheduledExecutor(ThreadFactory threadFactory) ；//threadFactory创建线程的工厂
     */
    @Test
    void newSingleThreadScheduledExecutor() {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        System.out.println("开始提交任务！");
        service.schedule(() -> {
            System.out.println("3秒后开始执行");
        }, 3, TimeUnit.SECONDS);
        notStop();
    }

    private void notStop() {
        while (true) {

        }
    }

}
 











































