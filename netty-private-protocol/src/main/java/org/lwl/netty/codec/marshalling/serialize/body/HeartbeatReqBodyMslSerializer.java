package org.lwl.netty.codec.marshalling.serialize.body;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.lwl.netty.codec.marshalling.serialize.IBodyMslSerializer;
import org.lwl.netty.message.Body;
import org.lwl.netty.message.body.HeartbeatReqBody;

/**
 * @author thinking_fioa
 * @createTime 2018/5/26
 * @description
 */


public final class HeartbeatReqBodyMslSerializer implements IBodyMslSerializer<HeartbeatReqBody> {

    private static final HeartbeatReqBodyMslSerializer INSTANCE = new HeartbeatReqBodyMslSerializer();
    private HeartbeatReqBodyMslSerializer(){}

    public static HeartbeatReqBodyMslSerializer getInstance() {
        return INSTANCE;
    }


    @Override
    public void serialize(ChannelHandlerContext ctx, ByteBuf outByteBuf, Body body) {

    }

    @Override
    public HeartbeatReqBody deserialize(ChannelHandlerContext ctx, ByteBuf inByteBuf) {
        return new HeartbeatReqBody();
    }
}
