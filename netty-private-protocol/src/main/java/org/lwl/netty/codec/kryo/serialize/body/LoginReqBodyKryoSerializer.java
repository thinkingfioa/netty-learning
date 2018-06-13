package org.lwl.netty.codec.kryo.serialize.body;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.lwl.netty.codec.kryo.serialize.KryoDecoder;
import org.lwl.netty.codec.kryo.serialize.KryoEncoder;
import org.lwl.netty.codec.marshalling.MslDecoder;
import org.lwl.netty.codec.marshalling.MslEncoder;
import org.lwl.netty.message.body.LoginReqBody;

/**
 * @author thinking_fioa
 * @createTime 2018/6/10
 * @description
 */


public class LoginReqBodyKryoSerializer extends Serializer<LoginReqBody> {
    @Override
    public void write(Kryo kryo, Output output, LoginReqBody login) {
        KryoEncoder.getInstance().writeString(output, login.getUserName());
        KryoEncoder.getInstance().writeString(output, login.getPassword());
    }

    @Override
    public LoginReqBody read(Kryo kryo, Input input, Class<LoginReqBody> aClass) {
        String userName = KryoDecoder.getInstance().readString(input);
        String password = KryoDecoder.getInstance().readString(input);

        LoginReqBody loginReqBody = new LoginReqBody();
        loginReqBody.setUserName(userName);
        loginReqBody.setPassword(password);

        return loginReqBody;
    }
}
