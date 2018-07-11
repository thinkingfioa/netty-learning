package org.lwl.netty.codec.other.kryo.serialize.body;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.lwl.netty.message.Body;

/**
 * @author thinking_fioa
 * @createTime 2018/5/26
 * @description
 */


public final class DefaultBodyKryoSerializer extends Serializer<Body> {
    @Override
    public void write(Kryo kryo, Output output, Body body) {

    }

    @Override
    public Body read(Kryo kryo, Input input, Class<Body> aClass) {
        return new Body();
    }
}
