package org.lwl.netty.codec.marshalling;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.lwl.netty.constant.ProtocolConfig;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author thinking_fioa
 * @createTime 2018/5/12
 * @description
 */


class Encoder {

    private static final MarshallingEncoderAdapter encoderAdapter = MarshallingAdapterFactory.buildEncoderAdapter();

    private static final Encoder INSTANCE = new Encoder();

    public static Encoder getInstance() {
        return INSTANCE;
    }

    public <T> void writeList(ChannelHandlerContext ctx, ByteBuf outByteBuf, List<T> valueList) throws Exception {
        if(null == valueList) {
            outByteBuf.writeInt(-1);

            return;
        }

        if(valueList.isEmpty()) {
            outByteBuf.writeInt(0);

            return;
        }

        outByteBuf.writeInt(valueList.size());

        for(T value: valueList) {
            writeObject(ctx, outByteBuf, value);
        }
    }

    public <T> void writeMap(ChannelHandlerContext ctx, ByteBuf outByteBuf, Map<String, T> valueMap) throws Exception {
        if(null == valueMap) {
            outByteBuf.writeInt(-1);

            return;
        }

        if(valueMap.isEmpty()) {
            outByteBuf.writeInt(0);

            return;
        }
        Set<Map.Entry<String, T>> entrySet = valueMap.entrySet();
        for(Map.Entry<String, T> entry: entrySet) {
            String key = entry.getKey();
            Object value = entry.getValue();
            // 写入key和value
            writeString(outByteBuf, key);
            writeObject(ctx, outByteBuf, value);
        }
    }

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

    public void writeObject(ChannelHandlerContext ctx, ByteBuf outByteBuf, Object valueObject) throws Exception {
        if(null == valueObject) {
            outByteBuf.writeInt(-1);

            return;
        }

        encoderAdapter.encode(ctx, valueObject, outByteBuf);
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
