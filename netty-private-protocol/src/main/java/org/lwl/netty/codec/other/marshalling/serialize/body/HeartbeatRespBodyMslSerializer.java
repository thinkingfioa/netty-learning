package org.lwl.netty.codec.other.marshalling.serialize.body;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.lwl.netty.codec.other.marshalling.serialize.IBodyMslSerializer;
import org.lwl.netty.message.Body;
import org.lwl.netty.message.body.HeartbeatRespBody;

/**
 * @author thinking_fioa
 * @createTime 2018/5/26
 * @description
 */


public final class HeartbeatRespBodyMslSerializer implements IBodyMslSerializer<HeartbeatRespBody> {

    private static final HeartbeatRespBodyMslSerializer INSTANCE = new HeartbeatRespBodyMslSerializer();
    private HeartbeatRespBodyMslSerializer(){}

    public static HeartbeatRespBodyMslSerializer getInstance() {
        return INSTANCE;
    }


    @Override
    public void serialize(ChannelHandlerContext ctx, ByteBuf outByteBuf, Body body) {

    }

    @Override
    public HeartbeatRespBody deserialize(ChannelHandlerContext ctx, ByteBuf inByteBuf) {
        return new HeartbeatRespBody();
    }
}
