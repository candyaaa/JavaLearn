package github.stone98.example.java.netty4.io.nio;

/**
 * @author stone
 * @date 2021/10/15
 */
public class NioServerMain {
    public static void main(String[] args) {
        MultiplexerTimeServer multiplexerTimeServer = new MultiplexerTimeServer(9999);
        Thread thread = new Thread(multiplexerTimeServer);
        thread.start();
    }
}
