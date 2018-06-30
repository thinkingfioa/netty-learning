package org.lwl.netty.codec.protobuf;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.codec.IMessageCodecUtil;
import org.lwl.netty.message.ProtocolMessage;

/**
 * @author thinking_fioa
 * @createTime 2018/6/27
 * @description protobuf 编码器
 */


public class ProtobufCodecUtil implements IMessageCodecUtil<ProtocolMessage>{
    private static final Logger LOGGER = LogManager.getLogger(ProtobufCodecUtil.class);

    @Override
    public void encode(ChannelHandlerContext ctx, ByteBuf outByteBuf, ProtocolMessage object) throws Exception {

    }

    @Override
    public ProtocolMessage decode(ChannelHandlerContext ctx, ByteBuf inByteBuf) throws Exception {
        return null;
    }
}
