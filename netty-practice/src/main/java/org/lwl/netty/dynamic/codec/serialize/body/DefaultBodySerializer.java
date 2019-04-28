package org.lwl.netty.dynamic.codec.serialize.body;

import io.netty.buffer.ByteBuf;
import org.lwl.netty.dynamic.codec.serialize.IBodySerializer;
import org.lwl.netty.dynamic.message.Body;

/**
 * @author thinking_fioa
 * @createTime 2018/7/29
 * @description
 */


public final class DefaultBodySerializer implements IBodySerializer <Body> {
    private static final DefaultBodySerializer INSTANCE = new DefaultBodySerializer();
    private DefaultBodySerializer(){}

    public static DefaultBodySerializer getInstance() {
        return INSTANCE;
    }

    @Override
    public void serialize(ByteBuf outByteBuf, Body body) {
    }

    @Override
    public Body deserialize(ByteBuf inByteBuf) {
        return new Body();
    }
}
