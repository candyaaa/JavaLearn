package org.stone98.example.juc.base;

/**
 * 指令重排序案例
 */
public class InstructionReorderingDemo {
    private static int num = 0;
    private static boolean ready = false;

    public static void main(String[] args) throws InterruptedException {
        ReadThread rt = new ReadThread();
        rt.start();
        WriteThread wt = new WriteThread();
        wt.start();
        Thread.sleep(10);
        rt.interrupt();
        System.out.println("main exit");
    }

    public static class ReadThread extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                if (ready) { // (1)
                    if (num == 0) {
                        System.out.println("shikui");
                    }
                    System.out.println(num + num); // (2)
                }
                System.out.println("read thread...");
            }
        }
    }

    public static class WriteThread extends Thread {
        public void run() {
            num = 2; // (3)
            ready = true; // (4)
            System.out.println("writeThread set over...");
        }
    }
}
