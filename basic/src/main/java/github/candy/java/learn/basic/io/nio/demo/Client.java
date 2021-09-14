package github.candy.java.learn.basic.io.nio.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.InetSocketAddress;

@Slf4j
public class Client {
    @Test
    public void test() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                        log.info("Received data");
                    }
                });
        ChannelFuture future = bootstrap.connect(new InetSocketAddress("www.manning.com", 80));
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    log.info("Connection established");
                } else {
                    log.info("Connection attempt failed");
                    future.cause().printStackTrace();
                }
            }
        });
    }

    public void test02() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new SimpleChannelInboundHandler<ByteBuf>() {
                    ChannelFuture connectFuture;

                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        Bootstrap bootstrap = new Bootstrap()
                                .channel(NioSocketChannel.class)
                                .handler(new SimpleChannelInboundHandler<ByteBuf>() {
                                    @Override
                                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
                                        System.out.println("Received data");
                                    }
                                });
                        bootstrap.group(ctx.channel().eventLoop());
                        connectFuture = bootstrap.connect(new InetSocketAddress("www.manning.com", 80));
                    }

                    @Override
                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                        if (connectFuture.isDone()) {// do something with the data
                        }
                    }
                });
        ChannelFuture future = bootstrap.bind(new InetSocketAddress(8080));
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    System.out.println("Server bound");
                } else {
                    System.err.println("Bind attempt failed");
                    channelFuture.cause().printStackTrace();
                }
            }
        });
    }

    /**
     * 注册多个ChannelHandler
     */
    @Test
    public void registerMultipleChannelHandler() throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializerImpl());
        ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(9999));
        channelFuture.sync();
    }

    class ChannelInitializerImpl extends ChannelInitializer {
        @Override
        protected void initChannel(Channel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline();
            pipeline.addLast(new HttpClientCodec());
            pipeline.addLast(new HttpObjectAggregator(Integer.MAX_VALUE));
        }
    }

    /**
     * channelOption and attr use
     */
//    @Test
//    public void channelOption() {
//        final AttributeKey<Integer> id = new AttributeKey<Integer>("ID");
//        Bootstrap bootstrap = new Bootstrap();
//        bootstrap.group(new NioEventLoopGroup())
//                .channel(NioSocketChannel.class)
//                .handler(new SimpleChannelInboundHandler<ByteBuf>() {
//
//                    @Override
//                    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
//                        Integer integer = ctx.channel().attr(id).get();
//                        // do something with the idValue
//                    }
//
//                    @Override
//                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
//                        log.info("Received data");
//                    }
//                });
//        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);
//        bootstrap.attr(id, 123456);
//        ChannelFuture future = bootstrap.connect(new InetSocketAddress("www.manning.com", 80));
//        future.syncUninterruptibly();
//    }
}
