package org.lwl.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.EncoderException;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.codec.marshalling.MarshallingCodecUtil;
import org.lwl.netty.constant.ProtocolConstant;

/**
 * @author thinking_fioa
 * @createTime 2018/4/26
 * @description 解码器
 */


public class ProtocolDataDecoder extends LengthFieldBasedFrameDecoder {
    private static final Logger LOGGER = LogManager.getLogger(ProtocolDataDecoder.class);

    private static final int MAX_FRAMELENGTH = ProtocolConstant.getMaxFramelength();
    private static final int LENGTH_FIELD_OFFSET = ProtocolConstant.getLengthFieldOffset();
    private static final int LENGTHFIELD_LENGTH = ProtocolConstant.getLengthfieldLength();
    private static final int LENGTH_ADJUSTMENT = ProtocolConstant.getLengthAdjustment();
    private static final int INITIAL_BYTES_TO_STRIP = ProtocolConstant.getInitialBytesToStrip();

    private IMessageCodecUtil codecUtil;

    public ProtocolDataDecoder() {
        super(MAX_FRAMELENGTH, LENGTH_FIELD_OFFSET, LENGTHFIELD_LENGTH, LENGTH_ADJUSTMENT, INITIAL_BYTES_TO_STRIP);
        this.codecUtil = MessageCodecUtilFactory.getCodecUtil();
    }

    @Override
    public Object decode(ChannelHandlerContext ctx, ByteBuf inByteBuf) throws Exception{
        ByteBuf frame = null;
        try {
            frame = (ByteBuf) super.decode(ctx, inByteBuf);
            if(null == frame) {
                return null;
            }

            LOGGER.info("  <-- decode msg. msgLen: {}", frame.writerIndex());
            return codecUtil.decode(ctx, frame);
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
