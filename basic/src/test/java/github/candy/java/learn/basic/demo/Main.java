package github.candy.java.learn.basic.demo;

import java.util.concurrent.CountDownLatch;

/**
 * @author candy
 * @date 2021/9/21 0:04
 */
public class Main {

    private CountDownLatch latch;

    private void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    private void change() {
        try {
            this.latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) throws InterruptedException {
//        Main m = new Main();
//        m.setLatch(new CountDownLatch(1));
//        Thread thread = new Thread(() -> m.change());
//        thread.start();
//        Thread.sleep(1000);
//        CountDownLatch latch = new CountDownLatch(1);
//        m.setLatch(latch);
//        latch.countDown();
//        m.change();
//        System.out.println(1);
//    }

    public static void main(String[] args) {
        try {
            System.out.println(1);
            throw new IndexOutOfBoundsException("1");
        } finally {
            System.out.println(2);
        }
    }
}
