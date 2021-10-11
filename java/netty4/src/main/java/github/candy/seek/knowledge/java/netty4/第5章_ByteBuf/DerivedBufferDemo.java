package github.candy.seek.knowledge.java.netty4.第5章_ByteBuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.nio.charset.Charset;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Derived buffer demo
 *
 * @author candy
 * @date 2021/10/11
 */
@Slf4j
public class DerivedBufferDemo {

    /**
     * 测试{@link ByteBuf}的切片以及共享的特性
     */
    @Test
    public void bufferSlice() {
        Charset charset = Charset.forName("utf-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", charset);
        ByteBuf slice = buf.slice(0, 15);
        log.info(slice.toString(charset));
        buf.setByte(0, 'J');
        assert buf.getByte(0) == slice.getByte(0);
    }

    /**
     * 测试{@link ByteBuf}的拷贝
     */
    @Test
    public void bufferCopy() {
        Charset charset = Charset.forName("utf-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", charset);
        ByteBuf copyBuf = buf.copy();
        copyBuf.setByte(0, 'J');
        assert copyBuf.getByte(0) != buf.getByte(0);
    }

    @Test
    public void bufferReadAndWrite() {
        // ignore
    }
}
