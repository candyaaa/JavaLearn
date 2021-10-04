package github.candy.java.learn.netty4.channel;

import github.candy.java.learn.netty4.SomeSocketClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import github.candy.java.learn.netty4.SomeSocketServerHandler;

@Slf4j
public class ChannelDemo {
    @Test
    public void nettyServer() throws InterruptedException {

        Thread thread = new Thread(() -> {
            while (true) {
                log.info("一共收到的消息：{}", SomeSocketServerHandler.count);
                log.info("存储的字符串引用:{}", SimpleDiscardHandler.list.size());
                log.info("存储的字符串内容:{}", SimpleDiscardHandler.list.toArray());

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();


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
                        pipeline.addLast(new MyChannelInboundHandler());
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(new SomeSocketServerHandler());
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
                            pipeline.addLast(new DiscardOutboundHandler());
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
}
