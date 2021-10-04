package github.candy.java.learn.basic.juc;

/**
 *  守护线程：如果线程中不存在非Daemon线程，则虚拟机就会退出，
 *  注意：daemon线程不能依赖finally进行清理工作，不会生效！
 */
public class Daemon {
    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner(),"daemonRunner");
        thread.setDaemon(true);
        thread.start();
    }

    static class DaemonRunner implements Runnable{

        @Override
        public void run() {
            try {
                SleepUtils.second(10);
            } finally {
                System.out.println("daemonThread finally run.");
            }
        }
    }
}
