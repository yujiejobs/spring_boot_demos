package com.soft.pool;

import cn.hutool.core.lang.Console;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class ThreadPoolTests {
    /**
     * corePoolSize 线程池核心线程大小
     * 线程池中会维护一个最小的线程数量，即使这些线程处理空闲状态，他们也不会被销毁，除非设置了allowCoreThreadTimeOut。
     * 这里的最小线程数量即是corePoolSize。
     */
    int corePoolSize = 2;
    /**
     * maximumPoolSize 线程池最大线程数量
     * 一个任务被提交到线程池以后，首先会找有没有空闲存活线程，如果有则直接将任务交给这个空闲线程来执行，如果没有则会缓存到工作队列（后面会介绍）中，
     * 如果工作队列满了，才会创建一个新线程，然后从工作队列的头部取出一个任务交由新线程来处理，而将刚提交的任务放入工作队列尾部。
     * 线程池不会无限制的去创建新线程，它会有一个最大线程数量的限制，这个数量即由maximunPoolSize指定。
     */
    int maximumPoolSize = 5;
    /**
     * keepAliveTime 空闲线程存活时间。
     * 一个线程如果处于空闲状态，并且当前的线程数量大于corePoolSize，那么在指定时间后，这个空闲线程会被销毁，这里的指定时间由keepAliveTime来设定。
     */
    long keepAliveTime = 10;
    /**
     * unit 空闲线程存活时间单位
     * keepAliveTime的计量单位
     */
    TimeUnit timeUnit = TimeUnit.SECONDS;

    /**
     * workQueue 工作队列
     * 新任务被提交后，会先进入到此工作队列中，任务调度时再从队列中取出任务。jdk中提供了四种工作队列：
     * <p>
     * 1.ArrayBlockingQueue
     * 基于数组的有界阻塞队列，按FIFO排序。新任务进来后，会放到该队列的队尾，有界的数组可以防止资源耗尽问题。当线程池中线程数量达到
     * corePoolSize后，再有新任务进来，则会将任务放入该队列的队尾，等待被调度。如果队列已经是满的，则创建一个新线程，如果线程数量已
     * 经达到maxPoolSize，则会执行拒绝策略。
     * <p>
     * LinkedBlockingQueue
     * 基于链表的无界阻塞队列（其实最大容量为 Interger.MAX），按照FIFO排序。由于该队列的近似无界性，当线程池中线程数量达到corePoolSize
     * 后，再有新任务进来，会一直存入该队列，而不会去创建新线程直到maxPoolSize，因此使用该工作队列时，参数maxPoolSize其实是不起作用的。
     * <p>
     * SynchronousQueue
     * 一个不缓存任务的阻塞队列，生产者放入一个任务必须等到消费者取出这个任务。也就是说新任务进来时，不会缓存，而是直接被调度执行该
     * 任务，如果没有可用线程，则创建新线程，如果线程数量达到maxPoolSize，则执行拒绝策略。
     * <p>
     * PriorityBlockingQueue
     * 具有优先级的无界阻塞队列，优先级通过参数Comparator实现。
     */
    BlockingQueue<Runnable> workQueue;

    /**
     * implements RejectedExecutionHandler
     * <p>
     * AbortPolicy: 直接抛出异常，阻止系统正常运行。
     * <p>
     * CallerRunsPolicy：该策略直接将任务交给调用者线程运行性能极有可能会急剧下降。
     * <p>
     * DiscardOldestPolicy：丢弃最老的一个请求，也就是即将被执行的一个任务，并尝试再次提交当前任务。
     * <p>
     * DiscardPolicy：该策略默默地丢弃无法处理的任务，不予任何处理。如果允许任务丢失，这是最好的一种方案。
     */
    RejectedExecutionHandler rejectedExecutionHandler;

    @Test
    void arrayBlockingQueue() {
        // 1.使用有界阻塞队列
        workQueue = new ArrayBlockingQueue<>(10);
        rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
                timeUnit, workQueue, rejectedExecutionHandler);
        // 2.批量提交任务
        for (int i = 0; i < 200; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int finalI = i;
            executor.execute(() -> {
                System.out.println("这是一个Runnable方法" + finalI);
            });
        }
    }

    @Test
    void linkedBlockingQueue() {
        // 1.使用有界阻塞队列
        workQueue = new ArrayBlockingQueue<>(10);
        rejectedExecutionHandler = new ThreadPoolExecutor.DiscardOldestPolicy();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
                timeUnit, workQueue, rejectedExecutionHandler);
        // 2.批量提交任务
        for (int i = 0; i < 200; i++) {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            int finalI = i;
            executor.execute(() -> {
                System.out.println("这是一个Runnable方法" + finalI + " 线程" + Thread.currentThread().getName());
            });
        }
    }


    @Test
    void Executors() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        while (true) {

        }
    }

}
 











































