package github.candy.java.learn.netty4.channel;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;

public class SimpleDiscardHandler extends SimpleChannelInboundHandler {
    public static List<Object> list = new ArrayList<>();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        list.add(msg);
    }
}
