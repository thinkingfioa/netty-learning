package org.lwl.netty.codec.other.kryo.serialize.body;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.lwl.netty.message.body.HeartbeatRespBody;

/**
 * @author thinking_fioa
 * @createTime 2018/6/10
 * @description
 */


public class HtRespBodyKryoSerializer extends Serializer<HeartbeatRespBody> {
    @Override
    public void write(Kryo kryo, Output output, HeartbeatRespBody heartbeatRespBody) {

    }

    @Override
    public HeartbeatRespBody read(Kryo kryo, Input input, Class<HeartbeatRespBody> aClass) {
        return new HeartbeatRespBody();
    }
}
