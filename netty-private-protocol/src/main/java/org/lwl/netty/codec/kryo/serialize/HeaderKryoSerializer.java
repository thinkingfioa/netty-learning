package org.lwl.netty.codec.kryo.serialize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.constant.MessageTypeEnum;
import org.lwl.netty.message.Header;

import java.util.Map;

/**
 * @author thinking_fioa
 * @createTime 2018/5/17
 * @description Kryo编码器。类: {@link Header}
 */


public class HeaderKryoSerializer extends Serializer<Header> {
    private static final Logger LOGGER = LogManager.getLogger(HeaderKryoSerializer.class);

    @Override
    public void write(Kryo kryo, Output output, Header header) {
        KryoEncoder.getInstance().writeInt(output, header.getMsgLen());
        KryoEncoder.getInstance().writeLong(output, header.getMsgNum());
        KryoEncoder.getInstance().writeString(output, header.getMsgType().getMsgType());
        KryoEncoder.getInstance().writeString(output, header.getMsgTime());
        KryoEncoder.getInstance().writeShort(output, header.getFlag());
        KryoEncoder.getInstance().writeByte(output, header.getOneByte());
        KryoEncoder.getInstance().writeMap(kryo, output, header.getAttachment());
    }

    @Override
    public Header read(Kryo kryo, Input input, Class<Header> aClass) {
        int msgLen = KryoDecoder.getInstance().readInt(input);
        long msgNum = KryoDecoder.getInstance().readLong(input);
        String msgType = KryoDecoder.getInstance().readString(input);
        String msgTime = KryoDecoder.getInstance().readString(input);
        short flag = KryoDecoder.getInstance().readShort(input);
        byte oneByte = KryoDecoder.getInstance().readByte(input);
        Map<String, Object> attachment = KryoDecoder.getInstance().readMap(kryo, input);

        Header header = new Header();
        header.setMsgLen(msgLen);
        header.setMsgNum(msgNum);
        header.setMsgType(MessageTypeEnum.getMsgTypeEnum(msgType));
        header.setMsgTime(msgTime);
        header.setFlag(flag);
        header.setOneByte(oneByte);
        header.setAttachment(attachment);

        return header;
    }
}
