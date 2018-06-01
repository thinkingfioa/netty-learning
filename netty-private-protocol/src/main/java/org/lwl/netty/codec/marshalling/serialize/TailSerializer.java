package org.lwl.netty.codec.marshalling.serialize;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.lwl.netty.codec.marshalling.Decoder;
import org.lwl.netty.codec.marshalling.Encoder;
import org.lwl.netty.message.Tail;
import org.lwl.netty.util.CommonUtil;

/**
 * @author thinking_fioa
 * @createTime 2018/5/15
 * @description
 */


public class TailSerializer {

    private static final TailSerializer INSTANCE = new TailSerializer();
    private TailSerializer(){}

    public static TailSerializer getInstance() {
        return INSTANCE;
    }

    public void serialize(ChannelHandlerContext ctx, ByteBuf outByteBuf, Tail msg) throws Exception {
        int checkSum = CommonUtil.calCheckSum(outByteBuf, outByteBuf.writerIndex());
        Encoder.getInstance().writeInt(outByteBuf, checkSum);
    }

    public Tail deserialize(ChannelHandlerContext ctx, ByteBuf inByteBuf) throws Exception {
        int checkSum = Decoder.getInstance().readInt(inByteBuf);

        Tail tail = new Tail();
        tail.setCheckSum(checkSum);

        return tail;
    }
}
