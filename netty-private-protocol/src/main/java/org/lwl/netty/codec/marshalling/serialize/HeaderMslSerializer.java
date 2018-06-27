package org.lwl.netty.codec.marshalling.serialize;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.lwl.netty.codec.marshalling.MslDecoder;
import org.lwl.netty.codec.marshalling.MslEncoder;
import org.lwl.netty.constant.MessageTypeEnum;
import org.lwl.netty.message.Header;

import java.util.Map;

/**
 * @author thinking_fioa
 * @createTime 2018/5/15
 * @description
 */

public class HeaderMslSerializer {

    private static final HeaderMslSerializer INSTANCE = new HeaderMslSerializer();
    private HeaderMslSerializer(){}

    public static HeaderMslSerializer getInstance() {
        return INSTANCE;
    }

    public void serialize(ChannelHandlerContext ctx, ByteBuf outByteBuf, Header header) throws Exception {
        MslEncoder.getInstance().writeLong(outByteBuf, header.getMsgNum());
        MslEncoder.getInstance().writeString(outByteBuf, header.getMsgType().getMsgType());
        MslEncoder.getInstance().writeString(outByteBuf, header.getMsgTime());
        MslEncoder.getInstance().writeShort(outByteBuf, header.getFlag());
        MslEncoder.getInstance().writeByte(outByteBuf, header.getOneByte());
        MslEncoder.getInstance().writeMap(ctx, outByteBuf, header.getAttachment());
    }

    public Header deserialize(ChannelHandlerContext ctx, ByteBuf inByteBuf) throws Exception{
        long msgNum = MslDecoder.getInstance().readLong(inByteBuf);
        String msgType = MslDecoder.getInstance().readString(inByteBuf);
        String msgTime = MslDecoder.getInstance().readString(inByteBuf);
        short flag = MslDecoder.getInstance().readShort(inByteBuf);
        byte oneByte = MslDecoder.getInstance().readByte(inByteBuf);
        Map<String, Object> attachment = MslDecoder.getInstance().readMap(ctx, inByteBuf);

        Header header = new Header();
        header.setMsgNum(msgNum);
        header.setMsgType(MessageTypeEnum.getMsgTypeEnum(msgType));
        header.setMsgTime(msgTime);
        header.setFlag(flag);
        header.setOneByte(oneByte);
        header.setAttachment(attachment);

        return header;
    }
}
