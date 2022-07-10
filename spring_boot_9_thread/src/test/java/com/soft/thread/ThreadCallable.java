package com.soft.thread;


import java.util.concurrent.Callable;

public class ThreadCallable implements Callable<String> {


    @Override
    public String call() throws Exception {
        Thread.sleep(2000);
        return "call执行的方法";
    }
}
