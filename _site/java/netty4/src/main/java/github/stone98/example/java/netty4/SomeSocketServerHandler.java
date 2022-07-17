package github.stone98.example.java.netty4;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author shikui@tidu.com
 * @date 2021/7/2
 */
@Slf4j
public class SomeSocketServerHandler extends ChannelInboundHandlerAdapter {

    public static volatile AtomicInteger count = new AtomicInteger(0);

    private final String result = "form server: %s";

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("client address:{}", ctx.channel().remoteAddress());
        String format = String.format(result, UUID.randomUUID());
        ctx.channel().writeAndFlush(format);
        ctx.fireChannelActive();
        TimeUnit.MICROSECONDS.sleep(500);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
