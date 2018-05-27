package org.lwl.netty.codec.marshalling.serialize.body;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.lwl.netty.codec.marshalling.serialize.IBodySerializer;
import org.lwl.netty.message.body.HeartbeatReqBody;

/**
 * @author thinking_fioa
 * @createTime 2018/5/26
 * @description
 */


public final class HeartbeatReqBodySerializer implements IBodySerializer<HeartbeatReqBody>{

    private static final HeartbeatReqBodySerializer INSTANCE = new HeartbeatReqBodySerializer();
    private HeartbeatReqBodySerializer(){}

    public static HeartbeatReqBodySerializer getInstance() {
        return INSTANCE;
    }


    @Override
    public void serialize(ChannelHandlerContext ctx, ByteBuf outByteBuf, HeartbeatReqBody body) {

    }

    @Override
    public HeartbeatReqBody deserialize(ChannelHandlerContext ctx, ByteBuf inByteBuf) {
        return new HeartbeatReqBody();
    }
}
