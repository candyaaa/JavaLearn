package pers.study.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {
    public static void main(String[] args) {
        usingCountDownLatch();
    }

    private static void usingCountDownLatch() {
        Thread[] thread = new Thread[100];
        CountDownLatch countDownLatch = new CountDownLatch(thread.length);
        // 创建线程
        for (int i = 0; i < thread.length; i++) {
            thread[i] = new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }

        // 启动线程
        for (int i = 0; i < thread.length; i++) {
            thread[i].start();
        }

        System.out.println("main thread block");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread end");
    }
}
