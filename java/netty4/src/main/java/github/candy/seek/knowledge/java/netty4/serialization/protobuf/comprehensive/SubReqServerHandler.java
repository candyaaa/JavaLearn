package github.candy.seek.knowledge.java.netty4.serialization.protobuf.comprehensive;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import serialization.protobuf.comprehensive.SubscribeReqProto;
import serialization.protobuf.comprehensive.SubscribeRespProto;

/**
 * @author candy
 * @date 2021/10/19
 */
@Slf4j
public class SubReqServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!(msg instanceof SubscribeReqProto.SubscribeReq)) {
            throw new IllegalArgumentException("The msg must be a subclass of SubscribeReq.");
        }
        SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq) msg;
        if ("Zhang San".equalsIgnoreCase(req.getUserName())) {
            log.info("Service accept client subscribe req:{}", req);
            ctx.writeAndFlush(resp(req.getSubReqID()));
        }
    }

    private SubscribeRespProto.SubscribeResp resp(int subReqID) {
        SubscribeRespProto.SubscribeResp.Builder builder = SubscribeRespProto.SubscribeResp.newBuilder();
        builder.setSubReqID(subReqID);
        builder.setRespCode(0);
        builder.setDesc("Netty book order succeed, 3 day later, sent to the designated address.");
        return builder.build();
    }
}
