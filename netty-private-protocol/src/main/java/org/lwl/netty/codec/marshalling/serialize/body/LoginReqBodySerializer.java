package org.lwl.netty.codec.marshalling.serialize.body;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.lwl.netty.codec.marshalling.Decoder;
import org.lwl.netty.codec.marshalling.Encoder;
import org.lwl.netty.codec.marshalling.serialize.IBodySerializer;
import org.lwl.netty.message.body.LoginReqBody;

import java.io.UnsupportedEncodingException;

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
    public void serialize(ChannelHandlerContext ctx, ByteBuf outByteBuf, LoginReqBody login) throws UnsupportedEncodingException {
        Encoder.getInstance().writeString(outByteBuf, login.getUserName());
        Encoder.getInstance().writeString(outByteBuf, login.getPassword());
    }

    @Override
    public LoginReqBody deserialize(ChannelHandlerContext ctx, ByteBuf inByteBuf) throws UnsupportedEncodingException {
        String userName = Decoder.getInstance().readString(inByteBuf);
        String password = Decoder.getInstance().readString(inByteBuf);

        LoginReqBody loginReqBody = new LoginReqBody();
        loginReqBody.setUserName(userName);
        loginReqBody.setPassword(password);

        return loginReqBody;
    }
}
