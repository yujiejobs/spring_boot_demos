package com.soft.thread;


/**
 * 描述: 实现 Runnable 接口方式创建线程
 *
 * @author yujie
 * @date 2022/7/17 11:01
 */
public class ThreadRunnable implements Runnable {


    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("执行了 ThreadRunnable 里的Run方法 ");
    }


}
