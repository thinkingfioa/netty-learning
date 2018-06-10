package org.lwl.netty.codec.marshalling.serialize.body;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.lwl.netty.codec.marshalling.serialize.IBodyMslSerializer;
import org.lwl.netty.message.Body;
import org.lwl.netty.message.body.LoginRespBody;

/**
 * @author thinking_fioa
 * @createTime 2018/5/26
 * @description
 */


public final class LoginRespBodyMslSerializer implements IBodyMslSerializer<LoginRespBody> {

    private static final LoginRespBodyMslSerializer INSTANCE = new LoginRespBodyMslSerializer();
    private LoginRespBodyMslSerializer(){}

    public static LoginRespBodyMslSerializer getInstance() {
        return INSTANCE;
    }


    @Override
    public void serialize(ChannelHandlerContext ctx, ByteBuf outByteBuf, Body body) {

    }

    @Override
    public LoginRespBody deserialize(ChannelHandlerContext ctx, ByteBuf inByteBuf) {
        return new LoginRespBody();
    }
}
