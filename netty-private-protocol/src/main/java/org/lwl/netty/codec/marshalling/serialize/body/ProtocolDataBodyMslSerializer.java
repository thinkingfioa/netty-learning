package org.lwl.netty.codec.marshalling.serialize.body;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.lwl.netty.codec.marshalling.MslDecoder;
import org.lwl.netty.codec.marshalling.MslEncoder;
import org.lwl.netty.codec.marshalling.serialize.IBodyMslSerializer;
import org.lwl.netty.message.Body;
import org.lwl.netty.message.body.ProtocolDataBody;

/**
 * @author thinking_fioa
 * @createTime 2018/5/26
 * @description
 */


public final class ProtocolDataBodyMslSerializer implements IBodyMslSerializer<ProtocolDataBody> {

    private static final ProtocolDataBodyMslSerializer INSTANCE = new ProtocolDataBodyMslSerializer();
    private ProtocolDataBodyMslSerializer(){}

    public static ProtocolDataBodyMslSerializer getInstance() {
        return INSTANCE;
    }

    @Override
    public void serialize(ChannelHandlerContext ctx, ByteBuf outByteBuf, Body body) {
        ProtocolDataBody dataBody = (ProtocolDataBody)body;

        MslEncoder.getInstance().writeLong(outByteBuf, dataBody.getPkgSum());
        MslEncoder.getInstance().writeLong(outByteBuf, dataBody.getPkgSequenceNum());
        MslEncoder.getInstance().writeInt(outByteBuf, dataBody.getContentLen());
        MslEncoder.getInstance().writeBytes(outByteBuf, dataBody.getContent());
    }

    @Override
    public ProtocolDataBody deserialize(ChannelHandlerContext ctx, ByteBuf inByteBuf) {
        long pkgSum = MslDecoder.getInstance().readLong(inByteBuf);
        long pkgSequenceNum = MslDecoder.getInstance().readLong(inByteBuf);
        int contentLen = MslDecoder.getInstance().readInt(inByteBuf);
        byte [] content = new byte[contentLen];
        MslDecoder.getInstance().readBytes(inByteBuf, content);

        ProtocolDataBody dataBody = new ProtocolDataBody();
        dataBody.setPkgSum(pkgSum);
        dataBody.setPkgSequenceNum(pkgSequenceNum);
        dataBody.setContentLen(contentLen);
        dataBody.setContent(content);

        return new ProtocolDataBody();
    }
}
