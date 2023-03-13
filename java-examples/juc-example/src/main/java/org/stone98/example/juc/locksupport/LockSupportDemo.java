package org.stone98.example.juc.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * @Description:
 * @Author: stone-98
 * @createTime: 2023年03月03日 10:13:13
 */
public class LockSupportDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            System.out.println("begin park");
            while (!Thread.currentThread().isInterrupted()){
                LockSupport.park();
            }
            System.out.println("unpark");
        });
        t.start();
        Thread.sleep(1000);
        System.out.println("main thread begin unpark!");
        t.interrupt();
    }
}
