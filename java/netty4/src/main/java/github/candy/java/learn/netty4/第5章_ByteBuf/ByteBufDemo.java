package github.candy.java.learn.netty4.第5章_ByteBuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.nio.ByteBuffer;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * byteBuf demo
 *
 * @author candy
 * @date 2021/9/24
 */
@Slf4j
public class ByteBufDemo {

    private void logByteBufInfo(ByteBuf byteBuf) {
        int offset = byteBuf.arrayOffset() + byteBuf.readerIndex();
        int length = byteBuf.readableBytes();
        log.info("ByteBuf offset:{}", offset);
        log.info("ByteBuf length:{}", length);
    }

    @Test
    public void heapBufferDemo() {
        ByteBuf heapBuffer = Unpooled.buffer(10, 15);
        if (heapBuffer.hasArray()) {
            logByteBufInfo(heapBuffer);
            ByteBuf byteBuf = heapBuffer.writeByte(1);
            logByteBufInfo(byteBuf);
            byte[] bytes = byteBuf.array();
            log.info(Arrays.toString(bytes));
        }
    }

    @Test
    public void directBufferDemo() {
        ByteBuf directBuffer = Unpooled.directBuffer(10, 15);
        directBuffer.writeByte(1);
        if (!directBuffer.hasArray()) {
            int readableBytes = directBuffer.readableBytes();
            byte[] array = new byte[readableBytes];
            // Transfers this buffer's data to the specified destination starting at the specified absolute index
            directBuffer.getBytes(directBuffer.readerIndex(), array);
            log.info(Arrays.toString(array));
        }
    }

    @Test
    public void jdkCompositeDemo() {
        ByteBuffer header = ByteBuffer.allocate(10);
        ByteBuffer body = ByteBuffer.allocate(10);
        // 使用一个ByteBuffer数组来保存
        ByteBuffer[] message = new ByteBuffer[]{header, body};
        // 使用一个ByteBuffer
        ByteBuffer compositeBuffer = ByteBuffer.allocate(header.remaining() + body.remaining());
        compositeBuffer.put(header);
        compositeBuffer.put(body);
        compositeBuffer.flip();
    }
}
