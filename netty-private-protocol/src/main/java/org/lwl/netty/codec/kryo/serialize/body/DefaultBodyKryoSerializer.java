package org.lwl.netty.codec.kryo.serialize.body;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.lwl.netty.codec.marshalling.serialize.IBodyMslSerializer;
import org.lwl.netty.message.Body;
import org.lwl.netty.message.Tail;

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
