package org.lwl.netty.codec.marshalling.serialize;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.lwl.netty.codec.marshalling.MslDecoder;
import org.lwl.netty.codec.marshalling.MslEncoder;
import org.lwl.netty.message.Tail;
import org.lwl.netty.util.CommonUtil;

/**
 * @author thinking_fioa
 * @createTime 2018/5/15
 * @description
 */


public class TailMslSerializer {

    private static final TailMslSerializer INSTANCE = new TailMslSerializer();
    private TailMslSerializer(){}

    public static TailMslSerializer getInstance() {
        return INSTANCE;
    }

    public void serialize(ChannelHandlerContext ctx, ByteBuf outByteBuf, Tail msg) throws Exception {
        int checkSum = CommonUtil.calCheckSum(outByteBuf, outByteBuf.writerIndex());
        MslEncoder.getInstance().writeInt(outByteBuf, checkSum);
    }

    public Tail deserialize(ChannelHandlerContext ctx, ByteBuf inByteBuf) throws Exception {
        int checkSum = MslDecoder.getInstance().readInt(inByteBuf);

        Tail tail = new Tail();
        tail.setCheckSum(checkSum);

        return tail;
    }
}
