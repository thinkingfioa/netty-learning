package org.lwl.netty.codec.marshalling.serialize.body;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.lwl.netty.codec.marshalling.serialize.IBodySerializer;
import org.lwl.netty.message.Body;

/**
 * @author thinking_fioa
 * @createTime 2018/5/26
 * @description
 */


public final class DefaultBodySerializer implements IBodySerializer<Body>{

    private static final DefaultBodySerializer INSTANCE = new DefaultBodySerializer();
    private DefaultBodySerializer(){}

    public static DefaultBodySerializer getInstance() {
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
