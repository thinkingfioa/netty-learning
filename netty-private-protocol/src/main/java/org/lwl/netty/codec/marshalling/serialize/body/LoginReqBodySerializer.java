package org.lwl.netty.codec.marshalling.serialize.body;

import io.netty.buffer.ByteBuf;
import org.lwl.netty.codec.marshalling.serialize.IBodySerializer;
import org.lwl.netty.message.body.LoginReqBody;

/**
 * @author thinking_fioa
 * @createTime 2018/5/26
 * @description
 */


public final class LoginReqBodySerializer implements IBodySerializer<LoginReqBody>{

    private static final LoginReqBodySerializer INSTANCE = new LoginReqBodySerializer();
    private LoginReqBodySerializer(){}

    public static LoginReqBodySerializer getInstance() {
        return INSTANCE;
    }


    @Override
    public void serialize(ByteBuf outByteBuf, LoginReqBody login) {

    }

    @Override
    public LoginReqBody deserialize(ByteBuf inByteBuf) {
        return new LoginReqBody();
    }
}
