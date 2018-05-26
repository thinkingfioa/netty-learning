package org.lwl.netty.codec.marshalling.serialize.body;

import io.netty.buffer.ByteBuf;
import org.lwl.netty.codec.marshalling.serialize.IBodySerializer;
import org.lwl.netty.message.body.LoginRespBody;

/**
 * @author thinking_fioa
 * @createTime 2018/5/26
 * @description
 */


public final class LoginRespBodySerializer implements IBodySerializer<LoginRespBody>{

    private static final LoginRespBodySerializer INSTANCE = new LoginRespBodySerializer();
    private LoginRespBodySerializer(){}

    public static LoginRespBodySerializer getInstance() {
        return INSTANCE;
    }


    @Override
    public void serialize(ByteBuf outByteBuf, LoginRespBody body) {

    }

    @Override
    public LoginRespBody deserialize(ByteBuf inByteBuf) {
        return new LoginRespBody();
    }
}
