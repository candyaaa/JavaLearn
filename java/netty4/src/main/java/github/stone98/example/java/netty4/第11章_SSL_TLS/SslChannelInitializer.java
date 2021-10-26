package github.stone98.example.java.netty4.第11章_SSL_TLS;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;
import javax.net.ssl.SSLEngine;

/**
 * {@link Channel}初始化，添加{@link SslHandler}
 *
 * @author candy
 * @date 2021/10/13
 */
public class SslChannelInitializer extends ChannelInitializer<Channel> {

    private final SslContext context;

    private final boolean startTls;

    public SslChannelInitializer(SslContext context, boolean startTls) {
        this.context = context;
        this.startTls = startTls;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        SSLEngine sslEngine = context.newEngine(channel.alloc());
        channel.pipeline().addFirst("ssl", new SslHandler(sslEngine, startTls));
    }
}
