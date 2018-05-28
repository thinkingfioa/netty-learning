package org.lwl.netty.codec.marshalling.serialize.body;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.lwl.netty.codec.marshalling.serialize.IBodySerializer;
import org.lwl.netty.message.Body;
import org.lwl.netty.message.body.HeartbeatRespBody;

/**
 * @author thinking_fioa
 * @createTime 2018/5/26
 * @description
 */


public final class HeartbeatRespBodySerializer implements IBodySerializer<HeartbeatRespBody>{

    private static final HeartbeatRespBodySerializer INSTANCE = new HeartbeatRespBodySerializer();
    private HeartbeatRespBodySerializer(){}

    public static HeartbeatRespBodySerializer getInstance() {
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
