package org.lwl.netty.dynamic.codec.serialize.body;

import io.netty.buffer.ByteBuf;
import org.lwl.netty.core.Decoder;
import org.lwl.netty.core.Encoder;
import org.lwl.netty.dynamic.codec.serialize.IBodySerializer;
import org.lwl.netty.dynamic.message.Body;
import org.lwl.netty.dynamic.message.body.SymEncryption;

/**
 * @author thinking_fioa
 * @createTime 2018/7/29
 * @description
 */


public class SymEncryptionSerializer implements IBodySerializer<SymEncryption> {
    private static final SymEncryptionSerializer INSTANCE = new SymEncryptionSerializer();

    private SymEncryptionSerializer() {
    }

    public static SymEncryptionSerializer getInstance() {
        return INSTANCE;
    }

    @Override
    public void serialize(ByteBuf outByteBuf, Body body) {
        SymEncryption SymEncryption = (SymEncryption) body;
        Encoder.writeString(outByteBuf, SymEncryption.getSymEncryptionMethod());
    }

    @Override
    public SymEncryption deserialize(ByteBuf inByteBuf) {
        String symEncryptionMethod = Decoder.readString(inByteBuf);

        SymEncryption symEncryption = new SymEncryption();
        symEncryption.setSymEncryptionMethod(symEncryptionMethod);

        return symEncryption;
    }
}
