package github.candy.java.learn.netty4;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author shikui@tidu.com
 * @date 2021/7/2
 */
@Slf4j
public class SomeSocketClientHandler extends ChannelInboundHandlerAdapter {

    private final String requestMsg = "from client: %s";


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println(msg);
        ChannelFuture future = ctx.channel().writeAndFlush(String.format(requestMsg, System.currentTimeMillis()));
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (!future.isSuccess()){
                    future.cause().printStackTrace();
                    future.channel().close();
                }else {
                    log.info("客户端写入数据成功！！！");
                }
            }
        });
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
