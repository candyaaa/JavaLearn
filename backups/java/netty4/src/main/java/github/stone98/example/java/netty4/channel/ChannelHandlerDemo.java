package github.stone98.example.java.netty4.channel;

import github.stone98.example.java.netty4.SomeSocketClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import github.stone98.example.java.netty4.SomeSocketServerHandler;

@Slf4j
public class ChannelHandlerDemo {
    @Test
    public void nettyServer() throws InterruptedException {
        NioEventLoopGroup parentGroup = new NioEventLoopGroup();
        NioEventLoopGroup childGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(parentGroup, childGroup)
                // config server IO model
                .channel(NioServerSocketChannel.class)
                // config server channel
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(new SomeSocketServerHandler());
                        pipeline.addLast(new InboundExceptionHandler());
                    }
                });
        ChannelFuture future = serverBootstrap.bind(8888).sync();
        log.info("server start...");
        future.channel().closeFuture().sync();
    }

    @Test
    public void nettyClient() {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    // Config client channel model.
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                            pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                            pipeline.addLast(new SomeSocketClientHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect("localhost", 8888).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            if (workerGroup != null) {
                workerGroup.shutdownGracefully();
            }
        }
    }

    class InboundExceptionHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            int i = 1 / 0;
            super.channelRead(ctx, msg);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            log.error("InboundExceptionHandler exception occurs");
        }
    }
}
