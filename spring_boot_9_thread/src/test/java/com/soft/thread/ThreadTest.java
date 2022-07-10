package com.soft.thread;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class ThreadTest {


    @Test
    void threadExtends() {
        ThreadExtends threadExtends = new ThreadExtends();
        threadExtends.start();
        System.out.println("threadExtends执行结束!");
        notEnd();
    }

    @Test
    void threadRunnable() {
        ThreadRunnable runnable = new ThreadRunnable();
        new Thread(runnable).start();
        System.out.println("threadRunnable执行结束!");
        notEnd();
    }

    @Test
    void threadCallable() throws ExecutionException, InterruptedException {
        ThreadCallable callable = new ThreadCallable();
        FutureTask<String> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        System.out.println("threadRunnable执行结束!");
        System.out.println(futureTask.get());
        notEnd();
    }

    private void notEnd() {
        while (true) {
            // 保持主线程不中断
        }
    }


}
