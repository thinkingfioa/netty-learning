package org.lwl.netty.dynamic.codec.serialize;

import io.netty.buffer.ByteBuf;
import org.lwl.netty.core.Decoder;
import org.lwl.netty.core.Encoder;
import org.lwl.netty.dynamic.DynamicMsgType;
import org.lwl.netty.dynamic.message.Header;
import org.lwl.netty.dynamic.message.Tail;
import sun.rmi.runtime.Log;

/**
 * @author thinking_fioa
 * @createTime 2018/7/29
 * @description
 */

public class HeaderSerializer {
    private static final HeaderSerializer INSTANCE = new HeaderSerializer();
    private HeaderSerializer(){}

    public static HeaderSerializer getInstance() {
        return INSTANCE;
    }

    public void serialize(ByteBuf outByteBuf, Header header) throws Exception {
        Encoder.writeLong(outByteBuf, header.getMsgNum());
        Encoder.writeByte(outByteBuf, header.getMsgType().getMsgType());
        Encoder.writeString(outByteBuf, header.getMsgTime());
    }

    public Header deserialize(ByteBuf inByteBuf) throws Exception {
        long msgNum  = Decoder.readLong(inByteBuf);
        Byte msgType = Decoder.readByte(inByteBuf);
        String msgTime = Decoder.readString(inByteBuf);

        Header header = new Header();
        header.setMsgNum(msgNum);
        header.setMsgType(DynamicMsgType.getMsgTypeEnum(msgType));
        header.setMsgTime(msgTime);

        return header;
    }
}
