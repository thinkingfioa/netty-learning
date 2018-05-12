package org.lwl.netty.codec.marshalling;

import io.netty.buffer.ByteBuf;
import org.lwl.netty.codec.IMessageCodecUtil;
import org.lwl.netty.message.ProtocolMessage;

/**
 * @author thinking_fioa
 * @createTime 2018/4/21
 * @description
 */


public class MarshallingCodecUtil implements IMessageCodecUtil<ProtocolMessage> {

    private static final MarshallingEncoder ENCODER = MarshallingEncoder.getInstance();
    private static final MarshallingDecoder DECODER = MarshallingDecoder.getInstance();

    @Override
    public void encode(ByteBuf outByteBuf, ProtocolMessage object) {

    }

    @Override
    public ProtocolMessage decode(ByteBuf inByteBuf) {
        return null;
    }
}
