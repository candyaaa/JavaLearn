package github.candy.seek.knowledge.java.netty4;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

/**
 * @author shikui@tidu.com
 * @date 2021/7/10
 */
public class FixedLengthFrameDecoder extends ByteToMessageDecoder {
    private int frameLength;

    public FixedLengthFrameDecoder(int frameLength) {
        if (frameLength < 0) {
            throw new IllegalArgumentException("frameLength must be a positive integer:" + frameLength);
        }
        this.frameLength = frameLength;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        while (byteBuf.readableBytes() >= frameLength) {
            ByteBuf buf = byteBuf.readBytes(frameLength);
            list.add(buf);
        }
    }


}
