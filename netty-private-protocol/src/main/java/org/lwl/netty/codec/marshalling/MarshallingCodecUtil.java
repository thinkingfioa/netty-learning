package org.lwl.netty.codec.marshalling;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.codec.IMessageCodecUtil;
import org.lwl.netty.codec.marshalling.serialize.HeaderMslSerializer;
import org.lwl.netty.codec.marshalling.serialize.IBodyMslSerializer;
import org.lwl.netty.codec.marshalling.serialize.TailMslSerializer;
import org.lwl.netty.constant.MessageTypeEnum;
import org.lwl.netty.message.Body;
import org.lwl.netty.message.Header;
import org.lwl.netty.message.ProtocolMessage;
import org.lwl.netty.message.Tail;
import org.lwl.netty.util.CommonUtil;

/**
 * @author thinking_fioa
 * @createTime 2018/4/21
 * @description
 */


public class MarshallingCodecUtil implements IMessageCodecUtil<ProtocolMessage> {
    private static final Logger LOGGER = LogManager.getLogger(MarshallingCodecUtil.class);
    private static final byte [] LENGTH_PLACEHOLDER = new byte[4];

    @Override
    public void encode(ChannelHandlerContext ctx, ByteBuf outByteBuf, ProtocolMessage msg) throws Exception {
        // 填入长度占位符
        outByteBuf.writeBytes(LENGTH_PLACEHOLDER);
        // Header
        HeaderMslSerializer.getInstance().serialize(ctx, outByteBuf, msg.getHeader());

        // Body
        IBodyMslSerializer<? extends Body> bodySerializer = getBodySerializer(msg.getBody().msgType());
        bodySerializer.serialize(ctx, outByteBuf, msg.getBody());

        // 更新Header消息中的长度域字段
        int msgLen = outByteBuf.writerIndex() + Tail.byteSize();
        outByteBuf.setInt(0, msgLen);
        // Tail
        TailMslSerializer.getInstance().serialize(ctx, outByteBuf, msg.getTail());
    }

    @Override
    public ProtocolMessage decode(ChannelHandlerContext ctx, ByteBuf inByteBuf) throws Exception {
        int msgLen = inByteBuf.readInt();
        // Header
        Header header = HeaderMslSerializer.getInstance().deserialize(ctx, inByteBuf);
        // Body
        MessageTypeEnum msgType = header.getMsgType();
        IBodyMslSerializer<? extends Body> bodySerializer = getBodySerializer(msgType);
        Body body = bodySerializer.deserialize(ctx, inByteBuf);

        int headBodyLen = inByteBuf.readerIndex();
        // Tail
        Tail tail = TailMslSerializer.getInstance().deserialize(ctx, inByteBuf);

        if(!checkSumRight(inByteBuf, headBodyLen, tail.getCheckSum())) {
            // checkSum wrong
            LOGGER.error("checkSum wrong. discard msg. msgType: {}", header.getMsgType());
            return null;
        }

        ProtocolMessage message = ProtocolMessage.createMsgOfDecode(header, body, tail);
        LOGGER.debug(" <-- read msgLen:{}, {}", msgLen, message);
        return message;
    }

    private boolean checkSumRight(ByteBuf inByteBuf, int length, int sendCheckSum) {
        int calCheckSum = CommonUtil.calCheckSum(inByteBuf, length);
        if(calCheckSum == sendCheckSum){
            return true;
        }

        return false;
    }

    private IBodyMslSerializer<? extends Body> getBodySerializer(MessageTypeEnum msgType) {
        return MarshallingAdapterFactory.getBodySerializer(msgType);
    }
}
