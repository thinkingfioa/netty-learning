package org.lwl.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.EncoderException;
import io.netty.handler.codec.MessageToByteEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.message.ProtocolMessage;

/**
 * @author thinking_fioa
 * @createTime 2018/4/26
 * @description 编码器
 */


public class ProtocolDataEncoder extends MessageToByteEncoder<ProtocolMessage>{
    private static final Logger LOGGER = LogManager.getLogger(ProtocolDataEncoder.class);

    private IMessageCodecUtil codecUtil;

    public ProtocolDataEncoder(IMessageCodecUtil codecUtil) {
        this.codecUtil = codecUtil;
    }

    public ProtocolDataEncoder() {
        this.codecUtil = null;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ProtocolMessage protocolMessage, ByteBuf byteBuf) throws Exception {
        if(null == protocolMessage || null == protocolMessage.getBody()) {
            LOGGER.error("protocolMessage is null. refuse encode");

            return;
        }
        try {
            ByteBuf outByteBuf = Unpooled.buffer();

            codecUtil.encode(outByteBuf, protocolMessage);

        } catch(Throwable cause) {
            LOGGER.error("Encode error.", cause);
        }

    }
}
