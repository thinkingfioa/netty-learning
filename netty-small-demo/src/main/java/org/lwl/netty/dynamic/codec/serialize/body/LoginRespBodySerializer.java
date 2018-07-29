package org.lwl.netty.dynamic.codec.serialize.body;

import io.netty.buffer.ByteBuf;
import org.lwl.netty.dynamic.codec.serialize.IBodySerializer;
import org.lwl.netty.dynamic.message.Body;
import org.lwl.netty.dynamic.message.body.LoginRespBody;

/**
 * @author thinking_fioa
 * @createTime 2018/7/29
 * @description
 */


public final class LoginRespBodySerializer implements IBodySerializer<LoginRespBody> {
    private static final LoginRespBodySerializer INSTANCE = new LoginRespBodySerializer();
    private LoginRespBodySerializer(){}

    public static LoginRespBodySerializer getInstance() {
        return INSTANCE;
    }

    @Override
    public void serialize(ByteBuf outByteBuf, Body body) {

    }

    @Override
    public LoginRespBody deserialize(ByteBuf inByteBuf) {
        return new LoginRespBody();
    }
}
