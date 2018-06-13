package org.lwl.netty.codec.kryo.serialize.body;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.lwl.netty.message.Tail;
import org.lwl.netty.message.body.HeartbeatReqBody;

/**
 * @author thinking_fioa
 * @createTime 2018/6/10
 * @description
 */


public class HtReqBodyKryoSerializer extends Serializer<HeartbeatReqBody> {
    @Override
    public void write(Kryo kryo, Output output, HeartbeatReqBody heartbeatReqBody) {

    }

    @Override
    public HeartbeatReqBody read(Kryo kryo, Input input, Class<HeartbeatReqBody> aClass) {
        return new HeartbeatReqBody();
    }
}
