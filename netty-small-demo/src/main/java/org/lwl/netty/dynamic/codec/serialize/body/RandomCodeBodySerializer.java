package org.lwl.netty.dynamic.codec.serialize.body;

import io.netty.buffer.ByteBuf;
import org.lwl.netty.core.Decoder;
import org.lwl.netty.core.Encoder;
import org.lwl.netty.dynamic.codec.serialize.IBodySerializer;
import org.lwl.netty.dynamic.message.Body;
import org.lwl.netty.dynamic.message.body.RandomCodeBody;

/**
 * @author thinking_fioa
 * @createTime 2018/7/29
 * @description
 */


public final class RandomCodeBodySerializer implements IBodySerializer<RandomCodeBody> {
    private static final RandomCodeBodySerializer INSTANCE = new RandomCodeBodySerializer();
    private RandomCodeBodySerializer(){}

    public static RandomCodeBodySerializer getInstance() {
        return INSTANCE;
    }

    @Override
    public void serialize(ByteBuf outByteBuf, Body body) {
        RandomCodeBody randomCodeBody = (RandomCodeBody)body;
        Encoder.writeString(outByteBuf, randomCodeBody.getRandomCode());
    }

    @Override
    public RandomCodeBody deserialize(ByteBuf inByteBuf) {
        String randomCode = Decoder.readString(inByteBuf);

        RandomCodeBody randomCodeBody =  new RandomCodeBody();
        randomCodeBody.setRandomCode(randomCode);

        return randomCodeBody;
    }
}
