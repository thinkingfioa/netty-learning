package org.lwl.netty.codec.kryo.serialize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.lwl.netty.codec.marshalling.MarshallingAdapterFactory;
import org.lwl.netty.codec.marshalling.MarshallingEncoderAdapter;
import org.lwl.netty.config.ProtocolConfig;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author thinking_fioa
 * @createTime 2018/5/12
 * @description
 */


public class KryoEncoder {

    private static final KryoEncoder INSTANCE = new KryoEncoder();

    public static KryoEncoder getInstance() {
        return INSTANCE;
    }

    public <T> void writeList(Kryo kryo, Output output, List<T> valueList) throws Exception {
        if(null == valueList) {
            output.writeInt(-1);

            return;
        }

        if(valueList.isEmpty()) {
            output.writeInt(0);

            return;
        }

        output.writeInt(valueList.size());

        for(T value: valueList) {
            writeObject(kryo, output, value);
        }
    }

    public <T> void writeMap(Kryo kryo, Output output, Map<String, T> valueMap) throws Exception {
        if(null == valueMap) {
            output.writeInt(-1);

            return;
        }

        if(valueMap.isEmpty()) {
            output.writeInt(0);

            return;
        }
        output.writeInt(valueMap.size());
        Set<Map.Entry<String, T>> entrySet = valueMap.entrySet();
        for(Map.Entry<String, T> entry: entrySet) {
            String key = entry.getKey();
            Object value = entry.getValue();
            // 写入key和value
            writeString(output, key);
            writeObject(kryo, output, value);
        }
    }

    /**
     * 序列化{@see String}对象.
     * @param output
     * @param value
     */
    public void writeString(Output output, String value) throws UnsupportedEncodingException {
        output.writeString(value);
    }

    public void writeObject(Kryo kryo, Output output, Object valueObject) throws Exception {
        if(null == valueObject) {
            throw new NullPointerException("valueObject is null.");
        }

        kryo.writeClassAndObject(output, valueObject);

    }

    public void writeBytes(Output output, byte[] bytes) {
        output.writeBytes(bytes);
    }

    /**
     * 序列化{@code Integer} 值
     * @param output
     * @param value
     */
    public void writeInt(Output output, int value) {
        output.writeInt(value);
    }

    public void writeLong(Output output, long value) {
        output.writeLong(value);
    }

    public void writeByte(Output output, byte value) {
        output.writeByte(value);
    }

    public void writeDouble(Output output, double value) {
        output.writeDouble(value);
    }

    public void writeShort(Output output, short value) {
        output.writeShort(value);
    }

}
