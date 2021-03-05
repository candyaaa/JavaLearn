package pers.study.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "进入停车场");
                    if (semaphore.availablePermits() == 0) {
                        System.out.println(Thread.currentThread().getName() + "车位不足");
                    }
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "成功停车");
                    Thread.sleep(new Random().nextInt(10000));
                    System.out.println(Thread.currentThread().getName() + "出停车场");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
