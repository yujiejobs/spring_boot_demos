package com.soft.thread;


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
