package github.stone98.example.java.netty4.serialization.protobuf.comprehensive;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import serialization.protobuf.comprehensive.SubscribeReqProto;

/**
 * @author candy
 * @date 2021/10/19
 */
@Slf4j
public class SubReqClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ctx.writeAndFlush(subReq(i));
        }
    }

    public SubscribeReqProto.SubscribeReq subReq(int subReqID) {
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqID(subReqID);
        builder.setUserName("Zhang San");
        builder.setProductName("Netty book for protobuf.");
        builder.setAddress("ChangSha YueLuQu");
        return builder.build();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("Receive server response : {}", msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
