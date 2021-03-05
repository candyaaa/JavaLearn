package pers.study.juc;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    static class TaskThread extends Thread {

        CyclicBarrier barrier;

        public TaskThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(getName() + " 到达餐厅");
                barrier.await();
                System.out.println(getName() + " 开始恰饭");

                Thread.sleep(2000);
                System.out.println(getName() + " 吃完了");
                barrier.await();
                System.out.println(getName() + " 开始跑路");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int threadNum = 5;
        CyclicBarrier barrier = new CyclicBarrier(threadNum, () -> System.out.println(Thread.currentThread().getName() + " 都完成任务"));

        for(int i = 0; i < threadNum; i++) {
            new TaskThread(barrier).start();
        }
    }
}
