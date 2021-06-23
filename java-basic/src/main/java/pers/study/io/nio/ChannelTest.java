package pers.study.io.nio;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
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
        while (true){
            buffer.clear();
            int read = outputStreamChannel.read(buffer);
            if (read == -1){
                break;
            }
            buffer.flip();
            inputStreamChannel.write(buffer);
        }
        outputStream.close();
        inputStream.close();
    }
}
