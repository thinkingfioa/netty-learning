package org.lwl.netty.codec.marshalling;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.lwl.netty.codec.IMessageCodecUtil;
import org.lwl.netty.codec.marshalling.serialize.HeaderSerializer;
import org.lwl.netty.codec.marshalling.serialize.IBodySerializer;
import org.lwl.netty.codec.marshalling.serialize.TailSerializer;
import org.lwl.netty.message.Body;
import org.lwl.netty.message.ProtocolMessage;

/**
 * @author thinking_fioa
 * @createTime 2018/4/21
 * @description
 */


public class MarshallingCodecUtil implements IMessageCodecUtil<ProtocolMessage> {

    private static final Encoder ENCODER = Encoder.getInstance();
    private static final Decoder DECODER = Decoder.getInstance();

    @Override
    public void encode(ChannelHandlerContext ctx, ByteBuf outByteBuf, ProtocolMessage msg) throws Exception {
        // Header
        HeaderSerializer.getInstance().serialize(ctx, outByteBuf, msg.getHeader());

        // Body
        IBodySerializer<? extends Body> bodySerializer = getBodySerializer(msg.getBody());
        bodySerializer.serialize(ctx, outByteBuf, msg.getBody());

        // Tail
        TailSerializer.getInstance().serialize(ctx, outByteBuf, msg.getTail());
    }

    @Override
    public ProtocolMessage decode(ChannelHandlerContext ctx, ByteBuf inByteBuf) {
        return null;
    }

    private IBodySerializer<? extends Body> getBodySerializer(Body body) {
        return MarshallingAdapterFactory.getBodySerializer(body.msgType());
    }
}
