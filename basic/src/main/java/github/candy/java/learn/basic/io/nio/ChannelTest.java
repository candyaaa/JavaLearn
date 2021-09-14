package github.candy.java.learn.basic.io.nio;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@Slf4j
public class ChannelTest {

    @Test
    public void channelWrite() throws IOException {
        final String context = "学好netty，写RPC";
        FileOutputStream outputStream = new FileOutputStream("f:\\test.txt");
        FileChannel channel = outputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(context.getBytes());
        byteBuffer.flip();
        channel.write(byteBuffer);
        outputStream.close();
        log.info("channelWrite finish...");
    }

    @Test
    public void channelRead() throws IOException {
        File file = new File("f:\\test.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        channel.read(byteBuffer);
        log.info(new String(byteBuffer.array()));
        fileInputStream.close();
        log.info("channelRead finish...");
    }

    @Test
    public void channelCopy() throws IOException {
        FileOutputStream outputStream = new FileOutputStream("f:\\test.txt");
        FileChannel outputStreamChannel = outputStream.getChannel();
        FileInputStream inputStream = new FileInputStream("f:\\copyTarget.txt");
        FileChannel inputStreamChannel = inputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            buffer.clear();
            int read = outputStreamChannel.read(buffer);
            if (read == -1) {
                break;
            }
            buffer.flip();
            inputStreamChannel.write(buffer);
        }
        outputStream.close();
        inputStream.close();
    }

    /**
     * 扩展ChannelInboundHandlerAdapter需要显示的释放消息
     */
    class DiscardHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            // 丢弃已接收的消息
            ReferenceCountUtil.release(msg);
        }
    }

    /**
     * SimpleChannelInboundHandler 会自动释放资源，所以你不应该存储指向任何消息的引用供将来使用，因为这些引用都将会失效。
     */
    class SimpleDiscardHandler extends SimpleChannelInboundHandler {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
            //  不需要显示的释放
        }
    }

    @Test
    public void test01() {

    }
}
