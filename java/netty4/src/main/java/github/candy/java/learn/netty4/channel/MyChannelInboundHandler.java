package github.candy.java.learn.netty4.channel;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyChannelInboundHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        log.info("channel已经被创建，但还未注册到 EventLoop");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("但还未注册到 EventLoop");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("Channel 处于活动状态（已经连接到它的远程节点）。它现在可以接收和发送数据了");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("Channel 没有连接到远程节点");
        super.channelInactive(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("当把 ChannelHandler 添加到 ChannelPipeline 中时被调用");
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("当从 ChannelPipeline 中移除 ChannelHandler 时被调用");
        super.handlerRemoved(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("当处理过程中在 ChannelPipeline 中有错误产生时被调用");
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("当Channel上的一个读操作完成时被调用");
        super.channelReadComplete(ctx);
    }
}
