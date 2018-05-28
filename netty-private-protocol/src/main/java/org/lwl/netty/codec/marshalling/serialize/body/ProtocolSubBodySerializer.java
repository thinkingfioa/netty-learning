package org.lwl.netty.codec.marshalling.serialize.body;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.lwl.netty.codec.marshalling.Decoder;
import org.lwl.netty.codec.marshalling.Encoder;
import org.lwl.netty.codec.marshalling.serialize.IBodySerializer;
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


public final class ProtocolSubBodySerializer implements IBodySerializer<ProtocolSubBody>{

    private static final ProtocolSubBodySerializer INSTANCE = new ProtocolSubBodySerializer();
    private ProtocolSubBodySerializer(){}

    public static ProtocolSubBodySerializer getInstance() {
        return INSTANCE;
    }


    @Override
    public void serialize(ChannelHandlerContext ctx, ByteBuf outByteBuf, Body body) throws Exception {
        ProtocolSubBody subBody = (ProtocolSubBody)body;
        Encoder.getInstance().writeList(ctx, outByteBuf, subBody.getDataTypeList());
    }

    @Override
    public ProtocolSubBody deserialize(ChannelHandlerContext ctx, ByteBuf inByteBuf) throws Exception {

        List<Object> objectList = Decoder.getInstance().readList(ctx, inByteBuf);

        List<ProtocolDataType> dataTypeList = new ArrayList<>();
        for(Object o : objectList) {
            if(o instanceof ProtocolDataType) {
                dataTypeList.add((ProtocolDataType)o);
            } else {
                throw new IllegalAccessError("decode error.");
            }
        }
        ProtocolSubBody subBody = new ProtocolSubBody();
        subBody.setDataTypeList(dataTypeList);

        return new ProtocolSubBody();

    }
}
