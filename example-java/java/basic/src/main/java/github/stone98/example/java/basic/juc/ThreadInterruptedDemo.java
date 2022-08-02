package github.stone98.example.java.basic.juc;

import java.util.concurrent.TimeUnit;

public class ThreadInterruptedDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

//        t1.start();
//
//        t2.start();
//
//        t3.start();

        t1.interrupt();



        Thread thread = Thread.currentThread();
        thread.interrupt();
        System.out.println(thread.isInterrupted());

        System.out.println("current thread is interrupted：" + Thread.interrupted());;
        System.out.println(thread.isInterrupted());
    }
}
