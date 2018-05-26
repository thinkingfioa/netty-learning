package org.lwl.netty.codec.marshalling.serialize.body;

import io.netty.buffer.ByteBuf;
import org.lwl.netty.codec.marshalling.serialize.IBodySerializer;
import org.lwl.netty.message.body.ProtocolDataBody;

/**
 * @author thinking_fioa
 * @createTime 2018/5/26
 * @description
 */


public final class ProtocolDataBodySerializer implements IBodySerializer<ProtocolDataBody>{

    private static final ProtocolDataBodySerializer INSTANCE = new ProtocolDataBodySerializer();
    private ProtocolDataBodySerializer(){}

    public static ProtocolDataBodySerializer getInstance() {
        return INSTANCE;
    }


    @Override
    public void serialize(ByteBuf outByteBuf, ProtocolDataBody body) {

    }

    @Override
    public ProtocolDataBody deserialize(ByteBuf inByteBuf) {
        return new ProtocolDataBody();
    }
}
