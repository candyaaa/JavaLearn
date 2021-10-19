package github.candy.seek.knowledge.java.netty4.粘包和拆包解决方案.基于行的分割符解码器;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;
import lombok.extern.slf4j.Slf4j;

/**
 * @author candy
 * @date 2021/10/18
 */
@Slf4j
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    private AtomicLong count = new AtomicLong(1);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        log.info("The time server receive {} request:{}", count.getAndIncrement(), body);
        String currentTime = "QUERY TIME REQUEST".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD REQUEST";
        currentTime = currentTime + System.getProperty("line.separator");
        ByteBuf res = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.write(res);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error(cause.getMessage());
        ctx.close();
    }
}
