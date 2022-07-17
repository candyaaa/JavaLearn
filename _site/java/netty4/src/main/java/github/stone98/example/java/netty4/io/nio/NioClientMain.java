package github.stone98.example.java.netty4.io.nio;

/**
 * @author stone
 * @date 2021/10/15
 */
public class NioClientMain {
    public static void main(String[] args) {
        TimeClientHandle timeClientHandle = new TimeClientHandle("127.0.0.1", 9999);
        Thread thread = new Thread(timeClientHandle);
        thread.start();
    }
}
