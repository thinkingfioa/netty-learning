package org.lwl.netty.server.handler.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.net.Protocol;
import org.lwl.netty.message.protobuf.*;

/**
 * @author thinking_fioa
 * @createTime 2018/7/15
 * @description
 */

public class HeartbeatServerHandler extends ChannelInboundHandlerAdapter{
    private static final Logger LOGGER = LogManager.getLogger(HeartbeatServerHandler.class);

    private int lossConnectCount = 0;

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        if(evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if(event.state() == IdleState.READER_IDLE) {
                lossConnectCount ++;
                if(lossConnectCount >=5) {
                    // 5次心跳客户端都没有给心跳回复，则关闭连接
                    ctx.channel().close();
                    LOGGER.error("heartbeat timeout, close.");
                    return ;
                }
            } else {
                LOGGER.warn("heartbeat write timeout. lossCount: {}", lossConnectCount);
                // sent heartbeat msg
                ctx.writeAndFlush(buildHtReqMsg());
            }
        } else {
            ctx.fireUserEventTriggered(evt);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ProtocolMessage.ProtocolMessageP message = (ProtocolMessage.ProtocolMessageP)msg;
        lossConnectCount=0;
        Header.MessageTypeEnum msgType = message.getHeader().getMsgType();
        if(Header.MessageTypeEnum.HEARTBEAT_REQ.equals(msgType)) {
            LOGGER.info("receive heartbeat req.");
            ctx.writeAndFlush(buildHtRespMsg());
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private ProtocolMessage.ProtocolMessageP buildHtReqMsg() {
        ProtocolMessage.ProtocolMessageP.Builder msgBuilder = ProtocolMessage.ProtocolMessageP.newBuilder();

        // Header
        Header.HeaderP.Builder headerBuilder = ProtobufServerCodecHelper.generateHeaderBuilder(Header.MessageTypeEnum.HEARTBEAT_REQ);
        msgBuilder.setHeader(headerBuilder);
        // Body
        HeartbeatReqBody.HeartbeatReqBodyP.Builder htReqBuilder = HeartbeatReqBody.HeartbeatReqBodyP.newBuilder();
        msgBuilder.setHeartbeatReqBody(htReqBuilder);
        // Tail
        Tail.TailP.Builder tailBuilder = ProtobufServerCodecHelper.generateTailBuilder(msgBuilder);
        msgBuilder.setTail(tailBuilder);

        return msgBuilder.build();
    }

    private ProtocolMessage.ProtocolMessageP buildHtRespMsg() {
        ProtocolMessage.ProtocolMessageP.Builder msgBuilder = ProtocolMessage.ProtocolMessageP.newBuilder();
        // Header
        Header.HeaderP.Builder headerBuilder = ProtobufServerCodecHelper.generateHeaderBuilder(Header.MessageTypeEnum.HEARTBEAT_RESP);
        msgBuilder.setHeader(headerBuilder);
        // Body
        HeartbeatRespBody.HeartbeatRespBodyP.Builder htRespBodyBuilder = HeartbeatRespBody.HeartbeatRespBodyP.newBuilder();
        msgBuilder.setHeartbeatRespBody(htRespBodyBuilder);
        // Tail
        Tail.TailP.Builder tailBuilder = ProtobufServerCodecHelper.generateTailBuilder(msgBuilder);
        msgBuilder.setTail(tailBuilder);
        return msgBuilder.build();
    }

}
