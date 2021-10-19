package github.candy.seek.knowledge.java.netty4.粘包和拆包解决方案.定长解码器;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.nio.ByteBuffer;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shikui@tidu.com
 * @date 2021/10/18
 */
@Slf4j
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!(msg instanceof String)) {
            throw new IllegalArgumentException("msg必须是String类型!!!");
        }
        log.info("Receive client:{}", msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
