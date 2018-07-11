package org.lwl.netty.codec.other.marshalling;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.lwl.netty.config.ProtocolConfig;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author thinking_fioa
 * @createTime 2018/5/12
 * @description
 */


public class MslDecoder {
    private static final MarshallingDecoderAdapter DECODER_ADAPTER = MarshallingAdapterFactory.buildDecoderAdapter();

    private static final MslDecoder INSTANCE = new MslDecoder();

    public static MslDecoder getInstance() {
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> readList(ChannelHandlerContext ctx, ByteBuf inByteBuf, Class<T> clazz) throws Exception {
        int size = inByteBuf.readInt();
        if(-1 == size) {
            return null;
        }
        if( 0 == size) {
            return new ArrayList<>();
        }

        List<T> list = new ArrayList<T>(size);
        for(int i =0;i<size; i++) {
            Object object = readObject(ctx, inByteBuf);
            list.add((T)object);
        }

        return list;
    }

    public Map<String, Object> readMap(ChannelHandlerContext ctx, ByteBuf inByteBuf) throws Exception {
        int size = inByteBuf.readInt();
        if(-1 == size) {
            return null;
        }
        if(0 == size) {
            return new HashMap<String, Object>();
        }

        Map<String, Object> valueMap = new HashMap<String, Object>(size);
        for(int i = 0; i<size; i++) {
            String key = readString(inByteBuf);
            Object value = readObject(ctx, inByteBuf);
            valueMap.put(key, value);
        }

        return valueMap;
    }

    public String readString(ByteBuf inByteBuf) throws UnsupportedEncodingException {
        int byteSize = inByteBuf.readInt();

        if(-1 == byteSize) {
            return null;
        }
        if(0 == byteSize) {
            return "";
        }

        byte[] bytes = new byte[byteSize];
        readBytes(inByteBuf, bytes);

        return new String(bytes, ProtocolConfig.getCharsetFormat());
    }

    public Object readObject(ChannelHandlerContext ctx, ByteBuf inByteBuf) throws Exception {
        return DECODER_ADAPTER.decode(ctx, inByteBuf);
    }

    public void readBytes(ByteBuf inByteBuf, byte[] dst) {
        inByteBuf.readBytes(dst);
    }

    public int readInt(ByteBuf inByteBuf) {
        return inByteBuf.readInt();
    }

    public long readLong(ByteBuf inByteBuf) {
        return inByteBuf.readLong();
    }

    public byte readByte(ByteBuf inByteBuf) {
        return inByteBuf.readByte();
    }

    public double readDouble(ByteBuf inByteBuf) {
        return inByteBuf.readDouble();
    }

    public short readShort(ByteBuf inByteBuf) {
        return inByteBuf.readShort();
    }
}
