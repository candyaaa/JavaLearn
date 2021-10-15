package github.candy.seek.knowledge.java.netty4.前置知识.nio;

/**
 * @author candy
 * @date 2021/10/15
 */
public class NioClientMain {
    public static void main(String[] args) {
        TimeClientHandle timeClientHandle = new TimeClientHandle("127.0.0.1", 9999);
        Thread thread = new Thread(timeClientHandle);
        thread.start();
    }
}
