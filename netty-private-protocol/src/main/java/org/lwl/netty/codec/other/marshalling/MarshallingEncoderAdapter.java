package org.lwl.netty.codec.other.marshalling;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingEncoder;

/**
 * @author thinking_fioa
 * @createTime 2018/5/13
 * @description
 */


public class MarshallingEncoderAdapter extends MarshallingEncoder {

    public MarshallingEncoderAdapter(MarshallerProvider provider) {
        super(provider);
    }

    @Override
    public void encode(ChannelHandlerContext ctx, Object msg, ByteBuf outByteBuf) throws Exception {
        super.encode(ctx, msg, outByteBuf);
    }
}
