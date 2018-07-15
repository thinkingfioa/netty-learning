package org.lwl.netty.server.handler.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.message.protobuf.Header;
import org.lwl.netty.message.protobuf.ProtocolDataBody;
import org.lwl.netty.message.protobuf.ProtocolMessage;
import org.lwl.netty.message.protobuf.Tail;

/**
 * @author thinking_fioa
 * @createTime 2018/7/15
 * @description
 */


public class ProtocolMsgSendHandler extends ChannelInboundHandlerAdapter{
    private static final Logger LOGGER = LogManager.getLogger(ProtocolMsgSendHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ProtocolMessage.ProtocolMessageP message = (ProtocolMessage.ProtocolMessageP)msg;
        Header.MessageTypeEnum msgType = message.getHeader().getMsgType();
        if(Header.MessageTypeEnum.PROTOCOL_SUB.equals(msgType)) {
            LOGGER.info("receive msgSub message.");
            sendPrtcData();
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private void sendPrtcData() {
        // TODO 提供for循环多次发送，比较各种编码器性能
    }

    private ProtocolMessage.ProtocolMessageP buildPrctDataMsg() {
        ProtocolMessage.ProtocolMessageP.Builder msgBuilder = ProtocolMessage.ProtocolMessageP.newBuilder();
        // Header
        Header.HeaderP.Builder headerBuilder = ProtobufServerCodecHelper.generateHeaderBuilder(Header.MessageTypeEnum.PROTOCOL_DATA);
        msgBuilder.setHeader(headerBuilder);
        // Body
        ProtocolDataBody.ProtocolDataBodyP.Builder dataBuilder = ProtocolDataBody.ProtocolDataBodyP.newBuilder();
        //TODO: 补充属性
        // Tail
        Tail.TailP.Builder tailBuilder = Tail.TailP.newBuilder();
        msgBuilder.setTail(tailBuilder);
        return msgBuilder.build();
    }
}
