package org.lwl.netty.dynamic.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.EncoderException;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.core.CommonUtil;
import org.lwl.netty.dynamic.DynamicConfig;
import org.lwl.netty.dynamic.DynamicMsgType;
import org.lwl.netty.dynamic.codec.serialize.DynamicSerializerFactory;
import org.lwl.netty.dynamic.codec.serialize.HeaderSerializer;
import org.lwl.netty.dynamic.codec.serialize.IBodySerializer;
import org.lwl.netty.dynamic.codec.serialize.TailSerializer;
import org.lwl.netty.dynamic.message.Body;
import org.lwl.netty.dynamic.message.DynamicMessage;
import org.lwl.netty.dynamic.message.Header;
import org.lwl.netty.dynamic.message.Tail;

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
            int msgLen = frame.readInt();
            // Header
            Header header = HeaderSerializer.getInstance().deserialize(frame);
            // Body
            DynamicMsgType msgType = header.getMsgType();
            IBodySerializer<? extends Body> bodySerializer = getBodySerializer(msgType);
            Body body = bodySerializer.deserialize(frame);

            int headBodyLen = frame.readerIndex();
            // tail
            Tail tail = TailSerializer.getInstance().deserialize(frame);

            if(!checkSumRight(frame, headBodyLen, tail.getCheckSum())) {
                // checkSum wrong
                LOGGER.error("checkSum wrong. discard msg. msgType: {}", header.getMsgType());
                return null;
            }

            DynamicMessage message = DynamicMessage.createMsgOfDecode(header, body, tail);
            LOGGER.debug(" <-- read msgLen:{}, {}", msgLen, message);
            return message;

        } catch (Throwable cause) {
            LOGGER.error("Decode error.", cause);
            throw new EncoderException("Decode error.");
        } finally {
            if(null != frame) {
                frame.release();
            }
        }
    }

    private IBodySerializer<? extends Body> getBodySerializer(DynamicMsgType msgType) {
        return DynamicSerializerFactory.getBodySerializer(msgType);
    }

    private boolean checkSumRight(ByteBuf inByteBuf, int length, int sendCheckSum) {
        int calCheckSum = CommonUtil.calCheckSum(inByteBuf, length);
        if(calCheckSum == sendCheckSum){
            return true;
        }

        return false;
    }
}
