package github.candy.java.learn.netty4;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

/**
 * @author candy
 * @date 2021/9/4 21:14
 */
public class HttpCompressionInitializer extends ChannelInitializer {

    private final boolean isClient;

    public HttpCompressionInitializer(boolean isClient) {
        this.isClient = isClient;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        if (isClient) {
//            pipeline
        }
    }
}
