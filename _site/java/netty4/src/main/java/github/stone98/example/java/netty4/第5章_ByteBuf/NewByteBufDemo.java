package github.stone98.example.java.netty4.第5章_ByteBuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.util.ReferenceCounted;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 创建{@link ByteBuf}的几种方式以及{@link ReferenceCounted}的了解
 *
 * @author candy
 * @date 2021/10/11
 */
@Slf4j
public class NewByteBufDemo {

    /**
     * Netty通过{@link ByteBufAllocator}实现了池化的能力
     */


    /**
     * 使用{@link UnpooledByteBufAllocator}（不具备池化能力）创建{@link ByteBuf}
     */
    @Test
    public void unpooledByteBufAllocatorNewByteBufDemo() {
        UnpooledByteBufAllocator unpooledByteBufAllocator = UnpooledByteBufAllocator.DEFAULT;
        ByteBuf buffer = unpooledByteBufAllocator.buffer();
        ByteBuf heapBuffer = unpooledByteBufAllocator.heapBuffer();
        ByteBuf directBuffer = unpooledByteBufAllocator.directBuffer();
        CompositeByteBuf compositeByteBuf = unpooledByteBufAllocator.compositeHeapBuffer();
    }

    /**
     * 使用{@link io.netty.buffer.Unpooled}创建{@link ByteBuf}
     */
    @Test
    public void unpooledNewByteDemo() {
        ByteBuf heapBuffer = Unpooled.buffer();
        ByteBuf directBuffer = Unpooled.directBuffer();
    }

    /**
     * {@link ByteBuf}和@{@link io.netty.buffer.ByteBufHolder}都实现了{@link ReferenceCounted}引入了引用计数的功能
     */
    @Test
    public void refCntDemo() {
        UnpooledByteBufAllocator unpooledByteBufAllocator = UnpooledByteBufAllocator.DEFAULT;
        ByteBuf byteBuf = unpooledByteBufAllocator.directBuffer();
        int refCnt = byteBuf.refCnt();
        log.info(String.valueOf(refCnt));
    }


}
