package org.lwl.netty.codec.other.marshalling.serialize.body;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.lwl.netty.codec.other.marshalling.serialize.IBodyMslSerializer;
import org.lwl.netty.message.Body;

/**
 * @author thinking_fioa
 * @createTime 2018/5/26
 * @description
 */


public final class DefaultBodyMslSerializer implements IBodyMslSerializer<Body> {

    private static final DefaultBodyMslSerializer INSTANCE = new DefaultBodyMslSerializer();
    private DefaultBodyMslSerializer(){}

    public static DefaultBodyMslSerializer getInstance() {
        return INSTANCE;
    }


    @Override
    public void serialize(ChannelHandlerContext ctx,  ByteBuf outByteBuf, Body body) {

    }

    @Override
    public Body deserialize(ChannelHandlerContext ctx, ByteBuf inByteBuf) {
        return new Body();
    }
}
