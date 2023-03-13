package org.stone98.example.juc.stopthread;


/**
 * @Description: 中断线程案例
 * @Author: stone-98
 * @createTime: 2023年03月03日 14:22:35
 */
public class StopThreadDemo {

    public static void main(String[] args) throws Exception {
//        test01();
//        test02();
        test03();
//        test04();
    }

    public static void test01() {
        Thread thread = new Thread(() -> {
            while (true) {
                Thread.yield();
                System.out.println("子线程运行中...");
            }
        });
        thread.start();
        thread.interrupt();
    }

    public static void test02() {
        Thread thread = new Thread(() -> {
            while (true) {
                Thread.yield();

                // 响应中断
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Java技术栈线程被中断，程序退出。");
                    return;
                }
            }
        });
        thread.start();
        thread.interrupt();
    }

    public static void test03() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                // 响应中断
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("线程被中断，程序退出。");
                    return;
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("线程休眠被中断，程序退出。");
                }
            }
        });
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }

    public static void test04() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                // 响应中断
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Java技术栈线程被中断，程序退出。");
                    return;
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("Java技术栈线程休眠被中断，程序退出。");
                    Thread.currentThread().interrupt();
                }
            }
        });
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }
}
