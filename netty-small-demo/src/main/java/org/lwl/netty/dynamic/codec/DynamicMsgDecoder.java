package org.lwl.netty.dynamic.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.EncoderException;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.dynamic.DynamicConfig;

/**
 * @author thinking_fioa
 * @createTime 2018/4/26
 * @description 解码器
 */


public class DynamicMsgDecoder extends LengthFieldBasedFrameDecoder {
    private static final Logger LOGGER = LogManager.getLogger(DynamicMsgDecoder.class);

    private static final int MAX_FRAMELENGTH = DynamicConfig.getMaxFramelength();
    private static final int LENGTH_FIELD_OFFSET = DynamicConfig.getLengthFieldOffset();
    private static final int LENGTHFIELD_LENGTH = DynamicConfig.getLengthfieldLength();
    private static final int LENGTH_ADJUSTMENT = DynamicConfig.getLengthAdjustment();
    private static final int INITIAL_BYTES_TO_STRIP = DynamicConfig.getInitialBytesToStrip();

    public DynamicMsgDecoder() {
        super(MAX_FRAMELENGTH, LENGTH_FIELD_OFFSET, LENGTHFIELD_LENGTH, LENGTH_ADJUSTMENT, INITIAL_BYTES_TO_STRIP);
    }

    @Override
    public Object decode(ChannelHandlerContext ctx, ByteBuf inByteBuf) throws Exception{
        ByteBuf frame = null;
        try {
            frame = (ByteBuf) super.decode(ctx, inByteBuf);
            if(null == frame) {
                return null;
            }
            //TODO 解码方式
            return null;
        } catch (Throwable cause) {
            LOGGER.error("Decode error.", cause);
            throw new EncoderException("Decode error.");
        } finally {
            if(null != frame) {
                frame.release();
            }
        }
    }
}
