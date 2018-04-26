package org.lwl.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.lwl.netty.message.ProtocolMessage;

/**
 * @author thinking_fioa
 * @createTime 2018/4/26
 * @description 编码器
 */


public class ProtocolDataEncoder extends MessageToByteEncoder<ProtocolMessage>{
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ProtocolMessage protocolMessage, ByteBuf byteBuf) throws Exception {

    }
}
