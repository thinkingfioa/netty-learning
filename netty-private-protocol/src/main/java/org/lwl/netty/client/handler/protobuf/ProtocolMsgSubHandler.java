package org.lwl.netty.client.handler.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.message.protobuf.*;
import org.lwl.netty.message.protobuf.ProtocolSubBody.ProtocolDataType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author thinking_fioa
 * @createTime 2018/7/11
 * @description
 */


public class ProtocolMsgSubHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LogManager.getLogger(ProtocolMsgSubHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ProtocolMessage.ProtocolMessageP msg = buildPtclSubMsg();
        LOGGER.info("MsgSub active. protocol msg sub sent.");
        ctx.writeAndFlush(msg);
    }

    private ProtocolMessage.ProtocolMessageP buildPtclSubMsg() {
        ProtocolMessage.ProtocolMessageP.Builder msgBuilder= ProtocolMessage.ProtocolMessageP.newBuilder();
        // Header
        Header.HeaderP.Builder headerBuilder = ProtobufCodecHelper.generateHeaderBuilder(Header.MessageTypeEnum.PROTOCOL_SUB);
        msgBuilder.setHeader(headerBuilder);
        // Body
        ProtocolSubBody.ProtocolSubBodyP.Builder subBuilder = ProtocolSubBody.ProtocolSubBodyP.newBuilder();
        List<ProtocolDataType> dataTypeList = new ArrayList<>();
        dataTypeList.add(ProtocolDataType.NEWS);
        dataTypeList.add(ProtocolDataType.SPORTS);
        dataTypeList.add(ProtocolDataType.ENTERTAINMENT);
        subBuilder.addAllDataTypeList(dataTypeList);
        // Tail
        Tail.TailP.Builder tailBuilder = ProtobufCodecHelper.generateTailBuilder(msgBuilder);
        msgBuilder.setTail(tailBuilder);

        return msgBuilder.build();
    }
}
