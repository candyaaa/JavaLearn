package github.candy.seek.knowledge.java.netty4.前置知识.nio;

/**
 * @author candy
 * @date 2021/10/15
 */
public class NioServerMain {
    public static void main(String[] args) {
        MultiplexerTimeServer multiplexerTimeServer = new MultiplexerTimeServer(9999);
        Thread thread = new Thread(multiplexerTimeServer);
        thread.start();
    }
}
