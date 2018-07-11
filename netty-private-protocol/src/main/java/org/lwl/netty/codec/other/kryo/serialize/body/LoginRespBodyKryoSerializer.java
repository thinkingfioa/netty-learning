package org.lwl.netty.codec.other.kryo.serialize.body;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.lwl.netty.message.body.LoginRespBody;

/**
 * @author thinking_fioa
 * @createTime 2018/6/10
 * @description
 */


public class LoginRespBodyKryoSerializer extends Serializer<LoginRespBody> {
    @Override
    public void write(Kryo kryo, Output output, LoginRespBody loginRespBody) {

    }

    @Override
    public LoginRespBody read(Kryo kryo, Input input, Class<LoginRespBody> aClass) {
        return new LoginRespBody();
    }
}
