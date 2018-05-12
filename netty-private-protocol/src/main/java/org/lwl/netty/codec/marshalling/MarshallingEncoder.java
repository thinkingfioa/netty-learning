package org.lwl.netty.codec.marshalling;

import io.netty.buffer.ByteBuf;
import org.lwl.netty.constant.ProtocolConfig;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author thinking_fioa
 * @createTime 2018/5/12
 * @description
 */


public class MarshallingEncoder {

    private static final MarshallingEncoder INSTANCE = new MarshallingEncoder();

    public static MarshallingEncoder getInstance() {
        return INSTANCE;
    }

    public void writeObject(ByteBuf outByteBuf, Object value) {
        if(null == value) {
            outByteBuf.writeInt(-1);

            return;
        }
        //todo::
    }

    public <T> void writeList(ByteBuf outByteBuf, List<T> valueList) {
        if(null == valueList) {
            outByteBuf.writeInt(-1);

            return;
        }
        // todo::
    }

    public

    /**
     * 序列化{@see String}对象. 1> value == null, 写入-1. 2> value == "", 写入0.
     * @param outByteBuf
     * @param value
     */
    public void writeString(ByteBuf outByteBuf, String value) throws UnsupportedEncodingException {
        if(null == value) {
            outByteBuf.writeInt(-1);

            return ;
        }

        if(value.isEmpty()) {
            outByteBuf.writeInt(0);

            return;
        }
        byte[] valueBytes = value.getBytes(ProtocolConfig.getCharsetFormat());
        outByteBuf.writeInt(valueBytes.length);
        outByteBuf.writeBytes(valueBytes);
    }

    public void writeBytes(ByteBuf outByteBuf, byte[] bytes) {
        outByteBuf.writeInt(bytes.length);
        outByteBuf.writeBytes(bytes);
    }

    /**
     * 序列化{@code Integer} 值
     * @param outByteBuf
     * @param value
     */
    public void writeInt(ByteBuf outByteBuf, int value) {
        outByteBuf.writeInt(value);
    }

    public void writeLong(ByteBuf outByteBuf, long value) {
        outByteBuf.writeLong(value);
    }

    public void writeByte(ByteBuf outByteBuf, byte value) {
        outByteBuf.writeByte(value);
    }

    public void writeDouble(ByteBuf outByteBuf, double value) {
        outByteBuf.writeDouble(value);
    }

    public void writeShort(ByteBuf outByteBuf, short value) {
        outByteBuf.writeShort(value);
    }

}
