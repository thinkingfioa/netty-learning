package org.lwl.netty.codec.marshalling;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;

/**
 * @author thinking_fioa
 * @createTime 2018/5/13
 * @description
 */


public class MarshallingDecoderAdapter extends MarshallingDecoder{

    public MarshallingDecoderAdapter(UnmarshallerProvider provider) {
        super(provider);
    }

    public MarshallingDecoderAdapter(UnmarshallerProvider provider, int maxObjectSize) {
        super(provider, maxObjectSize);
    }

    public Object decode(ChannelHandlerContext ctx, ByteBuf inByteBuf) throws Exception {
        return super.decode(ctx, inByteBuf);
    }
}
