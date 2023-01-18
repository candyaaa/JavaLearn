package github.stone98.example.java.netty4.第11章_SSL_TLS;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * HTTP消息聚合
 *
 * @author candy
 * @date 2021/10/13
 */
public class HttpAggregatorInitializer extends ChannelInitializer<Channel> {

    private final Boolean isClient;

    public HttpAggregatorInitializer(Boolean isClient) {
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
        pipeline.addLast("aggregator", new HttpObjectAggregator(512 * 1024));
    }
}
