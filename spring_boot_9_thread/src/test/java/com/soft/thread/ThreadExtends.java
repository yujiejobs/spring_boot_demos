package com.soft.thread;


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
