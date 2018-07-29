package org.lwl.netty.dynamic.codec.serialize.body;

import io.netty.buffer.ByteBuf;
import org.lwl.netty.dynamic.codec.serialize.IBodySerializer;
import org.lwl.netty.dynamic.message.Body;
import org.lwl.netty.dynamic.message.body.HeartbeatRespBody;

/**
 * @author thinking_fioa
 * @createTime 2018/7/29
 * @description
 */


public final class HtRespBodySerializer implements IBodySerializer<HeartbeatRespBody> {

    private static final HtRespBodySerializer INSTANCE = new HtRespBodySerializer();
    private HtRespBodySerializer(){}

    public static HtRespBodySerializer getInstance() {
        return INSTANCE;
    }

    @Override
    public void serialize(ByteBuf outByteBuf, Body body) {

    }

    @Override
    public HeartbeatRespBody deserialize(ByteBuf inByteBuf) {
        return new HeartbeatRespBody();
    }
}
