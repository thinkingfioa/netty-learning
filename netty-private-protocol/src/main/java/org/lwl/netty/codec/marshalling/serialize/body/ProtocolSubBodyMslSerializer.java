package org.lwl.netty.codec.marshalling.serialize.body;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.lwl.netty.codec.marshalling.MslDecoder;
import org.lwl.netty.codec.marshalling.MslEncoder;
import org.lwl.netty.codec.marshalling.serialize.IBodyMslSerializer;
import org.lwl.netty.constant.ProtocolDataType;
import org.lwl.netty.message.Body;
import org.lwl.netty.message.body.ProtocolSubBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author thinking_fioa
 * @createTime 2018/5/26
 * @description
 */


public final class ProtocolSubBodyMslSerializer implements IBodyMslSerializer<ProtocolSubBody> {

    private static final ProtocolSubBodyMslSerializer INSTANCE = new ProtocolSubBodyMslSerializer();
    private ProtocolSubBodyMslSerializer(){}

    public static ProtocolSubBodyMslSerializer getInstance() {
        return INSTANCE;
    }


    @Override
    public void serialize(ChannelHandlerContext ctx, ByteBuf outByteBuf, Body body) throws Exception {
        ProtocolSubBody subBody = (ProtocolSubBody)body;
        MslEncoder.getInstance().writeList(ctx, outByteBuf, subBody.getDataTypeList());
    }

    @Override
    public ProtocolSubBody deserialize(ChannelHandlerContext ctx, ByteBuf inByteBuf) throws Exception {

        List<ProtocolDataType> dataTypeList = MslDecoder.getInstance().readList(ctx, inByteBuf, ProtocolDataType.class);
        ProtocolSubBody subBody = new ProtocolSubBody();
        subBody.setDataTypeList(dataTypeList);

        return new ProtocolSubBody();

    }
}
