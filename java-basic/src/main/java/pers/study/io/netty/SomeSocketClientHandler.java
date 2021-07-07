package pers.study.io.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.util.concurrent.TimeUnit;

/**
 * @author shikui@tidu.com
 * @date 2021/7/2
 */
public class SomeSocketClientHandler extends ChannelInboundHandlerAdapter {

    private final String requestMsg = "from client: {}";


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println(msg);
        ctx.channel().writeAndFlush(String.format(requestMsg, System.currentTimeMillis()));
        TimeUnit.MILLISECONDS.sleep(5000);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx)
            throws Exception {
        ctx.channel().writeAndFlush(String.format(requestMsg, "begin talking"));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
