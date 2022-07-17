package com.soft.thread;


/**
 * 描述: 继承 Thread 方式创建线程
 *
 * @author yujie
 * @date 2022/7/17 11:02
 */
public class ThreadExtends extends Thread {

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("执行了ThreadExtends里的run方法");
    }

}
