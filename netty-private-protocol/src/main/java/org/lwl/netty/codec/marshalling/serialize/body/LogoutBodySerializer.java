package org.lwl.netty.codec.marshalling.serialize.body;

import io.netty.buffer.ByteBuf;
import org.lwl.netty.codec.marshalling.serialize.IBodySerializer;
import org.lwl.netty.message.body.LogoutBody;

/**
 * @author thinking_fioa
 * @createTime 2018/5/26
 * @description
 */


public final class LogoutBodySerializer implements IBodySerializer<LogoutBody>{

    private static final LogoutBodySerializer INSTANCE = new LogoutBodySerializer();
    private LogoutBodySerializer(){}

    public static LogoutBodySerializer getInstance() {
        return INSTANCE;
    }


    @Override
    public void serialize(ByteBuf outByteBuf, LogoutBody body) {

    }

    @Override
    public LogoutBody deserialize(ByteBuf inByteBuf) {
        return new LogoutBody();
    }
}
