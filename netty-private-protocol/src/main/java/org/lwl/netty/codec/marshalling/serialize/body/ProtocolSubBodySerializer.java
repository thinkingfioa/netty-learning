package org.lwl.netty.codec.marshalling.serialize.body;

import io.netty.buffer.ByteBuf;
import org.lwl.netty.codec.marshalling.serialize.IBodySerializer;
import org.lwl.netty.message.body.ProtocolSubBody;

/**
 * @author thinking_fioa
 * @createTime 2018/5/26
 * @description
 */


public final class ProtocolSubBodySerializer implements IBodySerializer<ProtocolSubBody>{

    private static final ProtocolSubBodySerializer INSTANCE = new ProtocolSubBodySerializer();
    private ProtocolSubBodySerializer(){}

    public static ProtocolSubBodySerializer getInstance() {
        return INSTANCE;
    }


    @Override
    public void serialize(ByteBuf outByteBuf, ProtocolSubBody body) {

    }

    @Override
    public ProtocolSubBody deserialize(ByteBuf inByteBuf) {
        return new ProtocolSubBody();
    }
}
