package pers.study.io.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author shikui@tidu.com
 * @date 2021/6/26
 */
@Slf4j
public class SelectorTest {

    @Test
    public void registerTest() throws IOException {
        ServerSocketChannel socketChannel = ServerSocketChannel.open();

        Selector selector = Selector.open();
        SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
//        log.info();
    }
}
