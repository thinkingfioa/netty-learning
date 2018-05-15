package org.lwl.netty.codec.marshalling.serialize;

import io.netty.buffer.ByteBuf;
import org.lwl.netty.codec.marshalling.Decoder;
import org.lwl.netty.codec.marshalling.Encoder;
import org.lwl.netty.message.Tail;

/**
 * @author thinking_fioa
 * @createTime 2018/5/15
 * @description
 */


public class TailSerializer {

    public void serialize(ByteBuf outByteBuf, Tail msg) throws Exception {
        Encoder.getInstance().writeInt(outByteBuf, msg.getCheckSum());
        Encoder.getInstance().writeInt(outByteBuf, msg.getCopyRightId());
        Encoder.getInstance().writeString(outByteBuf, msg.getCopyRight());
    }

    public Tail deserialize(ByteBuf inByteBuf) throws Exception {
        int checkSum = Decoder.getInstance().readInt(inByteBuf);
        int copyRightId = Decoder.getInstance().readInt(inByteBuf);
        String copyRight = Decoder.getInstance().readString(inByteBuf);

        Tail tail = new Tail();
        tail.setCheckSum(checkSum);
        tail.setCopyRightId(copyRightId);
        tail.setCopyRight(copyRight);

        return tail;
    }
}
