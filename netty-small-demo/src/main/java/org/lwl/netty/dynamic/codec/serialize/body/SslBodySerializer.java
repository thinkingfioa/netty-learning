package org.lwl.netty.dynamic.codec.serialize.body;

import io.netty.buffer.ByteBuf;
import org.lwl.netty.core.Decoder;
import org.lwl.netty.core.Encoder;
import org.lwl.netty.dynamic.codec.serialize.IBodySerializer;
import org.lwl.netty.dynamic.message.Body;
import org.lwl.netty.dynamic.message.body.RandomCodeBody;
import org.lwl.netty.dynamic.message.body.SslBody;

/**
 * @author thinking_fioa
 * @createTime 2018/7/29
 * @description
 */


public final class SslBodySerializer implements IBodySerializer<SslBody> {
    private static final SslBodySerializer INSTANCE = new SslBodySerializer();
    private SslBodySerializer(){}

    public static SslBodySerializer getInstance() {
        return INSTANCE;
    }

    @Override
    public void serialize(ByteBuf outByteBuf, Body body) {
        SslBody sslBody = (SslBody)body;
        Encoder.writeString(outByteBuf, sslBody.getSslVersion());
    }

    @Override
    public SslBody deserialize(ByteBuf inByteBuf) {
        String sslVersion = Decoder.readString(inByteBuf);

        SslBody sslBody =  new SslBody();
        sslBody.setSslVersion(sslVersion);

        return sslBody;
    }
}
