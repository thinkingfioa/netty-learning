package org.lwl.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.lwl.netty.constant.ProtocolConfig;

/**
 * @author thinking_fioa
 * @createTime 2018/4/26
 * @description 解码器
 */


public class ProtocolDataDecoder extends LengthFieldBasedFrameDecoder {

    private static final int MAX_FRAMELENGTH = ProtocolConfig.getMaxFramelength();
    private static final int LENGTH_FIELD_OFFSET = ProtocolConfig.getLengthFieldOffset();
    private static final int LENGTHFIELD_LENGTH = ProtocolConfig.getLengthfieldLength();
    private static final int LENGTH_ADJUSTMENT = ProtocolConfig.getLengthAdjustment();
    private static final int INITIAL_BYTES_TO_STRIP = ProtocolConfig.getInitialBytesToStrip();

    public ProtocolDataDecoder() {
        super(MAX_FRAMELENGTH, LENGTH_FIELD_OFFSET, LENGTHFIELD_LENGTH, LENGTH_ADJUSTMENT, INITIAL_BYTES_TO_STRIP);
    }

    @Override
    public Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception{
        return null;
    }
}
