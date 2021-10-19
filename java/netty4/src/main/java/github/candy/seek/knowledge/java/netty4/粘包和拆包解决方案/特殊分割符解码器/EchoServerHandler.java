package github.candy.seek.knowledge.java.netty4.粘包和拆包解决方案.特殊分割符解码器;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicLong;
import lombok.extern.slf4j.Slf4j;

/**
 * @author candy
 * @date 2021/10/18
 */
@Slf4j
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    private AtomicLong counter = new AtomicLong(1);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!(msg instanceof String)) {
            throw new IllegalArgumentException("msg的类型必须为String！！！");
        }
        String body = (String) msg;
        log.info("This is {} times receive client:{}", counter.getAndIncrement(), body);
        body += "$_";
        ByteBuf byteBuf = Unpooled.copiedBuffer(body.getBytes(StandardCharsets.UTF_8));
        ctx.writeAndFlush(byteBuf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
