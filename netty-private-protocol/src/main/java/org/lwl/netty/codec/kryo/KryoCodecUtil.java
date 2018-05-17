package org.lwl.netty.codec.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import org.lwl.netty.codec.IMessageCodecUtil;
import org.lwl.netty.config.ProtocolConfig;
import org.lwl.netty.message.ProtocolMessage;

import java.io.IOException;

/**
 * @author thinking_fioa
 * @createTime 2018/4/21
 * @description
 */


public class KryoCodecUtil implements IMessageCodecUtil<Object>{

    private static final int PKG_MAX_LEN = ProtocolConfig.getPkgMaxLen();

    @Override
    public void encode(ByteBuf outByteBuf, Object object) throws Exception {
        Kryo kryo = KryoHolder.get();
        ByteBufOutputStream byteBufOutputStream = null;
        try {
            byteBufOutputStream = new ByteBufOutputStream(outByteBuf);
            Output output = new Output(PKG_MAX_LEN, -1);
            output.setOutputStream(byteBufOutputStream);
            kryo.writeClassAndObject(output, object);

            output.flush();
            output.close();
        } finally {
            if(null != byteBufOutputStream) {
                byteBufOutputStream.close();
            }
        }
    }

    @Override
    public Object decode(ByteBuf inByteBuf) {
        if(null == inByteBuf) {
            return null;
        }
        Input input = new Input(new ByteBufInputStream(inByteBuf));
        Kryo kryo = KryoHolder.get();
        return kryo.readClassAndObject(input);
    }
}
