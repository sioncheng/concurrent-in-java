package com.github.sioncheng.cij;

import java.io.IOException;
import java.util.concurrent.*;

public class ThreadPool {

    public static void main(String[] args) throws IOException {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2
                , 3
                , 3
                , TimeUnit.SECONDS
                , new ArrayBlockingQueue<Runnable>(3));


        threadPoolExecutor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("rejected");
                //r.run();
            }
        });

        for (int i = 0 ; i < 20; i++) {
            threadPoolExecutor.execute(new Runnable() {
                public void run() {
                    System.out.println(String.format("<<< executed in thread %s", Thread.currentThread().getName()));
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                    System.out.println(String.format(">>> executed in thread %s", Thread.currentThread().getName()));
                }
            });
        }

        System.in.read();
    }
}
