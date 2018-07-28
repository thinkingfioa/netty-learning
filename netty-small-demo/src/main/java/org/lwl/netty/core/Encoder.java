package org.lwl.netty.core;

import io.netty.buffer.ByteBuf;
import org.lwl.netty.Config;

/**
 * @author thinking_fioa
 * @createTime 2018/5/12
 * @description Netty编码器
 */


public final class Encoder {

    /**
     * 序列化{@see String}对象. 1> value == null, 写入-1. 2> value == "", 写入0.
     */
    public static void writeString(ByteBuf outByteBuf, String value) {
        if(null == value) {
            outByteBuf.writeInt(-1);

            return ;
        }

        if(value.isEmpty()) {
            outByteBuf.writeInt(0);

            return;
        }
        byte[] valueBytes = value.getBytes(Config.getCharsetFormat());
        outByteBuf.writeInt(valueBytes.length);
        outByteBuf.writeBytes(valueBytes);
    }

    public static void writeBytes(ByteBuf outByteBuf, byte[] bytes) {
        outByteBuf.writeBytes(bytes);
    }

    /**
     * 序列化{@code Integer} 值
     */
    public static void writeInt(ByteBuf outByteBuf, int value) {
        outByteBuf.writeInt(value);
    }

    public static void writeLong(ByteBuf outByteBuf, long value) {
        outByteBuf.writeLong(value);
    }

    public static void writeByte(ByteBuf outByteBuf, byte value) {
        outByteBuf.writeByte(value);
    }

    public static void writeDouble(ByteBuf outByteBuf, double value) {
        outByteBuf.writeDouble(value);
    }

    public static void writeShort(ByteBuf outByteBuf, short value) {
        outByteBuf.writeShort(value);
    }

}
