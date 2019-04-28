package org.lwl.netty.dynamic.codec.serialize.body;

import io.netty.buffer.ByteBuf;
import org.lwl.netty.core.Decoder;
import org.lwl.netty.core.Encoder;
import org.lwl.netty.dynamic.codec.serialize.IBodySerializer;
import org.lwl.netty.dynamic.message.Body;
import org.lwl.netty.dynamic.message.body.HeartbeatRespBody;
import org.lwl.netty.dynamic.message.body.LoginReqBody;

/**
 * @author thinking_fioa
 * @createTime 2018/7/29
 * @description
 */


public final class LoginReqBodySerializer implements IBodySerializer<LoginReqBody> {
    private static final LoginReqBodySerializer INSTANCE = new LoginReqBodySerializer();
    private LoginReqBodySerializer(){}

    public static LoginReqBodySerializer getInstance() {
        return INSTANCE;
    }

    @Override
    public void serialize(ByteBuf outByteBuf, Body body) {
        LoginReqBody login = (LoginReqBody)body;
        Encoder.writeString(outByteBuf, login.getUserName());
        Encoder.writeString(outByteBuf, login.getPassword());
    }

    @Override
    public LoginReqBody deserialize(ByteBuf inByteBuf) {
        String userName = Decoder.readString(inByteBuf);
        String password = Decoder.readString(inByteBuf);

        LoginReqBody loginReqBody = new LoginReqBody();
        loginReqBody.setUserName(userName);
        loginReqBody.setPassword(password);

        return loginReqBody;
    }
}
