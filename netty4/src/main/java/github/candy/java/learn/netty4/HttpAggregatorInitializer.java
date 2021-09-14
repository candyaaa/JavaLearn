package github.candy.java.learn.netty4;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
 * @author candy
 * @date 2021/9/4 20:39
 */
public class HttpAggregatorInitializer extends ChannelInitializer {

    private final SslContext context;
    private final boolean isClient;

    public HttpAggregatorInitializer(boolean isClient, SslContext sslContext) {
        this.context = sslContext;
        this.isClient = isClient;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        if (isClient) {
            pipeline.addLast("codec", new HttpClientCodec());
        } else {
            pipeline.addLast("codec", new HttpServerCodec());
        }
        // 将最大的消息大小为512kb的HttpObjectAggregator添加到ChannelPipeline
        pipeline.addLast("aggregator", new HttpObjectAggregator(512 * 1024));
        // 如果是服务器，则添加httpContentCompressor来压缩数据（如果客户端支持的话）
        pipeline.addLast("compressor", new HttpContentCompressor());
        // 将SslHandler添加到ChannelPipeline中来使用HTTPS
        SSLEngine sslEngine = context.newEngine(channel.alloc());
        pipeline.addLast("ssl", new SslHandler(sslEngine));
    }
}
