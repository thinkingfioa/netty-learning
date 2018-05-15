package org.lwl.netty.codec.marshalling.serialize;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.lwl.netty.codec.marshalling.Decoder;
import org.lwl.netty.codec.marshalling.Encoder;
import org.lwl.netty.message.Header;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author thinking_fioa
 * @createTime 2018/5/15
 * @description
 */


public class HeaderSerializer {

    public void serialize(ChannelHandlerContext ctx, ByteBuf outByteBuf, Header header) throws Exception {
        Encoder.getInstance().writeInt(outByteBuf, header.getMsgLen());
        Encoder.getInstance().writeLong(outByteBuf, header.getSessionID());
        Encoder.getInstance().writeString(outByteBuf, header.getMsgType());
        Encoder.getInstance().writeString(outByteBuf, header.getSenderName());
        Encoder.getInstance().writeShort(outByteBuf, header.getFlag());
        Encoder.getInstance().writeByte(outByteBuf, header.getOneByte());
        Encoder.getInstance().writeMap(ctx, outByteBuf, header.getAttachment());
    }

    public Header deserialize(ChannelHandlerContext ctx, ByteBuf inByteBuf) throws Exception{
        int msgLen = Decoder.getInstance().readInt(inByteBuf);
        long sessionID = Decoder.getInstance().readLong(inByteBuf);
        String msgType = Decoder.getInstance().readString(inByteBuf);
        String senderName = Decoder.getInstance().readString(inByteBuf);
        short flag = Decoder.getInstance().readShort(inByteBuf);
        byte oneByte = Decoder.getInstance().readByte(inByteBuf);
        Map<String, Object> attachment = Decoder.getInstance().readMap(ctx, inByteBuf);

        Header header = new Header();
        header.setMsgLen(msgLen);
        header.setSessionID(sessionID);
        header.setMsgType(msgType);
        header.setSenderName(senderName);
        header.setFlag(flag);
        header.setOneByte(oneByte);
        header.setAttachment(attachment);

        return header;
    }
}
