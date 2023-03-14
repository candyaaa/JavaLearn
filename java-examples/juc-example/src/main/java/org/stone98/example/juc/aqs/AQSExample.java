package org.stone98.example.juc.aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: stone-98
 * @createTime: 2023年03月11日 10:24:31
 */
public class AQSExample {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
    }
}
