package org.lwl.netty.codec.kryo.serialize.body;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.lwl.netty.message.Tail;
import org.lwl.netty.message.body.LogoutBody;

/**
 * @author thinking_fioa
 * @createTime 2018/6/10
 * @description
 */


public class LogoutBodyKryoSerializer extends Serializer<LogoutBody> {
    @Override
    public void write(Kryo kryo, Output output, LogoutBody logoutBody) {

    }

    @Override
    public LogoutBody read(Kryo kryo, Input input, Class<LogoutBody> aClass) {
        return new LogoutBody();
    }
}
