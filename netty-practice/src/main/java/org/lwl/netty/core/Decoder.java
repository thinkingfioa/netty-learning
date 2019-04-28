package org.lwl.netty.core;

import io.netty.buffer.ByteBuf;
import org.lwl.netty.Config;

/**
 * @author thinking_fioa
 * @createTime 2018/5/12
 * @description Netty解码器
 */


public final class Decoder {

    public static String readString(ByteBuf inByteBuf) {
        int byteSize = inByteBuf.readInt();

        if(-1 == byteSize) {
            return null;
        }
        if(0 == byteSize) {
            return "";
        }

        byte[] bytes = new byte[byteSize];
        readBytes(inByteBuf, bytes);

        return new String(bytes, Config.getCharsetFormat());
    }

    public static void readBytes(ByteBuf inByteBuf, byte[] dst) {
        inByteBuf.readBytes(dst);
    }

    public static int readInt(ByteBuf inByteBuf) {
        return inByteBuf.readInt();
    }

    public static long readLong(ByteBuf inByteBuf) {
        return inByteBuf.readLong();
    }

    public static byte readByte(ByteBuf inByteBuf) {
        return inByteBuf.readByte();
    }

    public static double readDouble(ByteBuf inByteBuf) {
        return inByteBuf.readDouble();
    }

    public static short readShort(ByteBuf inByteBuf) {
        return inByteBuf.readShort();
    }
}
