package org.lwl.netty.codec.other.kryo.serialize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author thinking_fioa
 * @createTime 2018/5/12
 * @description
 */


public class KryoDecoder {

    private static final KryoDecoder INSTANCE = new KryoDecoder();

    public static KryoDecoder getInstance() {
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> readList(Kryo kryo, Input input, Class<T> clazz) {
        int size = input.readInt();
        if(-1 == size) {
            return null;
        }
        if( 0 == size) {
            return new ArrayList<>();
        }

        List<T> list = new ArrayList<>(size);
        for(int i =0;i<size; i++) {
            list.add((T)readObject(kryo, input));
        }

        return list;
    }

    public Map<String, Object> readMap(Kryo kryo, Input input) {
        int size = input.readInt();
        if(-1 == size) {
            return null;
        }
        if(0 == size) {
            return new HashMap<String, Object>();
        }

        Map<String, Object> valueMap = new HashMap<String, Object>(size);
        for(int i = 0; i<size; i++) {
            String key = readString(input);
            Object value = readObject(kryo, input);
            valueMap.put(key, value);
        }

        return valueMap;
    }

    public String readString(Input input) {
        return input.readString();
    }

    public Object readObject(Kryo kryo, Input input) {
        return kryo.readClassAndObject(input);
    }

    public void readBytes(Input input, byte[] dst) {
        input.readBytes(dst);
    }

    public int readInt(Input input) {
        return input.readInt();
    }

    public long readLong(Input input) {
        return input.readLong();
    }

    public byte readByte(Input input) {
        return input.readByte();
    }

    public double readDouble(Input input) {
        return input.readDouble();
    }

    public short readShort(Input input) {
        return input.readShort();
    }
}
