package github.candy.java.learn.basic.io.nio.server;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO server and client demo.
 *
 * @author Candy
 */
@Slf4j
public class NIOServerDemo {

    private final int port = 8888;

    private final int waitTime = 1000;

    private final String ip = "localhost";

    private final String clientSendContext = "Hello NIO. I am candy.";

    @Test
    public void server() throws Exception {
        // Create Address.
        InetSocketAddress inetSocketAddress = new InetSocketAddress(port);
        // Create Selector.
        Selector selector = Selector.open();
        // Create and config ServerSocketChannel.
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(inetSocketAddress);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            Thread.sleep(200);
            // No event.
            if (selector.select(waitTime) == 0) {
                log.info("Server wait {} ms, No connection.", waitTime);
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                // New client connection.
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }

                // Read event occurred.
                if (selectionKey.isReadable()) {
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
                    channel.read(byteBuffer);
                    String message = new String(byteBuffer.array());
                    log.info("Message received from client:{}", message);
                }

                // Remove selectionKey.
                iterator.remove();
            }
        }
    }

    @Test
    public void client() throws Exception {
        // Create SocketChannel.
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        // Create Address.
        InetSocketAddress inetSocketAddress = new InetSocketAddress(ip, port);

        // Try connection.
        if (!socketChannel.connect(inetSocketAddress)) {
            Thread.sleep(200);
            while (!socketChannel.finishConnect()) {
                log.info("Connection failure. Try it again");
            }
        }

        ByteBuffer byteBuffer = ByteBuffer.wrap(clientSendContext.getBytes());
        socketChannel.write(byteBuffer);
        log.info("Client send finish.");

    }
}
