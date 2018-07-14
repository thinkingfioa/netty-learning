package org.lwl.netty.client.handler.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.message.protobuf.Tail;
import org.lwl.netty.message.protobuf.Header;
import org.lwl.netty.message.protobuf.HeartbeatReqBody;
import org.lwl.netty.message.protobuf.HeartbeatRespBody;
import org.lwl.netty.message.protobuf.ProtocolMessage;

/**
 * @author thinking_fioa
 * @createTime 2018/7/8
 * @description
 */

public class HeartbeatClientHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LogManager.getLogger(HeartbeatClientHandler.class);

    private int lossConnectCount = 0;

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        if(evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent)evt;
            if(event.state() == IdleState.READER_IDLE) {
                lossConnectCount++;
                if(lossConnectCount >= 5) {
                    // 5次服务端未返回心跳应答消息，则关闭连接
                    ctx.channel().close();
                    LOGGER.error("heartbeat timeout, close.");
                    return ;
                }
            } else if(event.state() == IdleState.WRITER_IDLE) {
                LOGGER.warn("heartbeat timeout. lossCount: {}", lossConnectCount);
                // sent heartbeat msg
                ctx.writeAndFlush(buildHtReqMsg());
            }
        } else {
            ctx.fireUserEventTriggered(evt);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ProtocolMessage.ProtocolMessageP message = (ProtocolMessage.ProtocolMessageP) msg;
        final Header.MessageTypeEnum msgType = message.getHeader().getMsgType();
        lossConnectCount = 0;
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
        Header.HeaderP.Builder headerBuilder = ProtobufCodecHelper.generateHeaderBuilder(Header.MessageTypeEnum.HEARTBEAT_REQ);
        msgBuilder.setHeader(headerBuilder);
        // Body
        HeartbeatReqBody.HeartbeatReqBodyP.Builder htReqBodyBuilder = HeartbeatReqBody.HeartbeatReqBodyP.newBuilder();
        msgBuilder.setHeartbeatReqBody(htReqBodyBuilder.build());
        // Tail
        Tail.TailP.Builder tailBuilder = ProtobufCodecHelper.generateTailBuilder(msgBuilder);
        msgBuilder.setTail(tailBuilder);
        return msgBuilder.build();
    }

    private ProtocolMessage.ProtocolMessageP buildHtRespMsg() {
        ProtocolMessage.ProtocolMessageP.Builder msgBuilder = ProtocolMessage.ProtocolMessageP.newBuilder();
        // Header
        Header.HeaderP.Builder headerBuilder = ProtobufCodecHelper.generateHeaderBuilder(Header.MessageTypeEnum.HEARTBEAT_RESP);
        msgBuilder.setHeader(headerBuilder);
        // Body
        HeartbeatRespBody.HeartbeatRespBodyP.Builder htResqBodyBuilder = HeartbeatRespBody.HeartbeatRespBodyP.newBuilder();
        msgBuilder.setHeartbeatRespBody(htResqBodyBuilder);
        // Tail
        Tail.TailP.Builder tailBuilder = ProtobufCodecHelper.generateTailBuilder(msgBuilder);
        msgBuilder.setTail(tailBuilder);
        return msgBuilder.build();
    }
}
