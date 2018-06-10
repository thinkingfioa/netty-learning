package org.lwl.netty.codec.marshalling.serialize.body;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.lwl.netty.codec.marshalling.MslDecoder;
import org.lwl.netty.codec.marshalling.MslEncoder;
import org.lwl.netty.codec.marshalling.serialize.IBodyMslSerializer;
import org.lwl.netty.message.Body;
import org.lwl.netty.message.body.LoginReqBody;

import java.io.UnsupportedEncodingException;

/**
 * @author thinking_fioa
 * @createTime 2018/5/26
 * @description
 */


public final class LoginReqBodyMslSerializer implements IBodyMslSerializer<LoginReqBody> {

    private static final LoginReqBodyMslSerializer INSTANCE = new LoginReqBodyMslSerializer();
    private LoginReqBodyMslSerializer(){}

    public static LoginReqBodyMslSerializer getInstance() {
        return INSTANCE;
    }


    @Override
    public void serialize(ChannelHandlerContext ctx, ByteBuf outByteBuf, Body body) throws UnsupportedEncodingException {
        LoginReqBody login = (LoginReqBody)body;
        MslEncoder.getInstance().writeString(outByteBuf, login.getUserName());
        MslEncoder.getInstance().writeString(outByteBuf, login.getPassword());
    }

    @Override
    public LoginReqBody deserialize(ChannelHandlerContext ctx, ByteBuf inByteBuf) throws UnsupportedEncodingException {
        String userName = MslDecoder.getInstance().readString(inByteBuf);
        String password = MslDecoder.getInstance().readString(inByteBuf);

        LoginReqBody loginReqBody = new LoginReqBody();
        loginReqBody.setUserName(userName);
        loginReqBody.setPassword(password);

        return loginReqBody;
    }
}
