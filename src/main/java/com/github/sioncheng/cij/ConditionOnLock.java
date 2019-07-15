package com.github.sioncheng.cij;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionOnLock {

    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();

    public static void main(String[] args) throws Exception {

        System.out.println("start at " + System.currentTimeMillis());

        new Thread(new Runnable() {
            public void run() {
                quietSleep(1000);
                lock.lock();
                condition.signalAll();
                lock.unlock();
            }
        }).start();

        lock.lock();

        condition.await();

        lock.unlock();

        System.out.println("end at " + System.currentTimeMillis());
    }

    private static void quietSleep(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException ie) {

        }
    }
}
