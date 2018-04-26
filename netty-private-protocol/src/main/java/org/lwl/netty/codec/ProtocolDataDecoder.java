package org.lwl.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author thinking_fioa
 * @createTime 2018/4/26
 * @description 解码器
 */


public class ProtocolDataDecoder extends LengthFieldBasedFrameDecoder {

    private static final int maxFrameLength = 0;
    private static final int lengthFieldOffset = 2;
    private static final int lengthFieldLength = 0;
    private static final int lengthAdjustment = 0;
    private static final int initialBytesToStrip = 0;

    public ProtocolDataDecoder() {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
    }

    @Override
    public Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception{
        return null;
    }
}
