package org.lwl.netty.dynamic.codec.serialize;

import io.netty.buffer.ByteBuf;
import org.lwl.netty.core.CommonUtil;
import org.lwl.netty.core.Decoder;
import org.lwl.netty.core.Encoder;
import org.lwl.netty.dynamic.message.Tail;

/**
 * @author thinking_fioa
 * @createTime 2018/7/29
 * @description
 */


public class TailSerializer {
    private static final TailSerializer INSTANCE = new TailSerializer();
    private TailSerializer(){}

    public static TailSerializer getInstance() {
        return INSTANCE;
    }

    public void serialize(ByteBuf outByteBuf, Tail msg) throws Exception {
        int checkSum = CommonUtil.calCheckSum(outByteBuf, outByteBuf.writerIndex());
        Encoder.writeInt(outByteBuf, checkSum);
    }

    public Tail deserialize(ByteBuf inByteBuf) throws Exception {
        int checkSum = Decoder.readInt(inByteBuf);

        Tail tail = new Tail();
        tail.setCheckSum(checkSum);

        return tail;
    }
}
