package org.lwl.netty.codec.marshalling.serialize.body;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.lwl.netty.codec.marshalling.Decoder;
import org.lwl.netty.codec.marshalling.Encoder;
import org.lwl.netty.codec.marshalling.serialize.IBodySerializer;
import org.lwl.netty.message.Body;
import org.lwl.netty.message.body.LoginReqBody;
import org.lwl.netty.message.body.ProtocolDataBody;

/**
 * @author thinking_fioa
 * @createTime 2018/5/26
 * @description
 */


public final class ProtocolDataBodySerializer implements IBodySerializer<ProtocolDataBody>{

    private static final ProtocolDataBodySerializer INSTANCE = new ProtocolDataBodySerializer();
    private ProtocolDataBodySerializer(){}

    public static ProtocolDataBodySerializer getInstance() {
        return INSTANCE;
    }

    @Override
    public void serialize(ChannelHandlerContext ctx, ByteBuf outByteBuf, Body body) {
        ProtocolDataBody datBody = (ProtocolDataBody)body;

        Encoder.getInstance().writeLong(outByteBuf, datBody.getPkgSum());
        Encoder.getInstance().writeLong(outByteBuf, datBody.getPkgSequenceNum());
        Encoder.getInstance().writeInt(outByteBuf, datBody.getContentLen());
        Encoder.getInstance().writeBytes(outByteBuf, datBody.getContent());
    }

    @Override
    public ProtocolDataBody deserialize(ChannelHandlerContext ctx, ByteBuf inByteBuf) {
        long pkgSum = Decoder.getInstance().readLong(inByteBuf);
        long pkgSequenceNum = Decoder.getInstance().readLong(inByteBuf);
        int contentLen = Decoder.getInstance().readInt(inByteBuf);
        byte [] content = new byte[contentLen];
        Decoder.getInstance().readBytes(inByteBuf, content);

        ProtocolDataBody dataBody = new ProtocolDataBody();
        dataBody.setPkgSum(pkgSum);
        dataBody.setPkgSequenceNum(pkgSequenceNum);
        dataBody.setContentLen(contentLen);
        dataBody.setContent(content);

        return new ProtocolDataBody();
    }
}
