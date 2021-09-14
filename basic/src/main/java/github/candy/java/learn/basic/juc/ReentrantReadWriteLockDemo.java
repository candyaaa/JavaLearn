package github.candy.java.learn.basic.juc;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo {
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();

    public void read() {
        try {
            readLock.lock();
            System.out.println("当前线程:" + Thread.currentThread().getName() + "进入...");
            Thread.sleep(3000);
            System.out.println("当前线程:" + Thread.currentThread().getName() + "退出...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    public void write() {
        try {
            writeLock.lock();
            System.out.println("当前线程:" + Thread.currentThread().getName() + "进入...");
            Thread.sleep(3000);
            System.out.println("当前线程:" + Thread.currentThread().getName() + "退出...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {

        final ReentrantReadWriteLockDemo urrw = new ReentrantReadWriteLockDemo();

        Thread t1 = new Thread(() -> urrw.read(), "t1");
        Thread t2 = new Thread(() -> urrw.read(), "t2");
        Thread t3 = new Thread(() -> urrw.write(), "t3");
        Thread t4 = new Thread(() -> urrw.write(), "t4");
//		t1.start();
//		t2.start();
//		t1.start(); // R
//		t3.start(); // W
        t3.start();
        t4.start();

    }
}
