package github.stone98.example.java.netty4.example;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;

/**
 * @author candy
 * @date 2021/10/18
 */
@Slf4j
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private byte[] req;

    public TimeClientHandler() {
        req = ("QUERY TIME REQUEST" + System.getProperty("line.separator")).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 100; i++) {
            ByteBuf buffer = Unpooled.buffer(req.length);
            buffer.writeBytes(req);
            ctx.writeAndFlush(buffer);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String time = (String) msg;
        log.info("Now is:{}", time);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("Unexpected exception from downstream:{}", cause.getMessage());
        ctx.close();
    }
}
