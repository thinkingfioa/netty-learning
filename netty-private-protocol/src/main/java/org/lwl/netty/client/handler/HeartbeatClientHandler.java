package org.lwl.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.constant.MessageTypeEnum;
import org.lwl.netty.message.ProtocolMessage;
import org.lwl.netty.message.body.HeartbeatReqBody;
import org.lwl.netty.message.body.HeartbeatRespBody;

/**
 * @author thinking_fioa
 * @createTime 2018/5/24
 * @description Client心跳，负责监听读空闲
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
                // sent heartbeat msg
                LOGGER.warn("heartbeat req msg sent.");
                ctx.writeAndFlush(buildHtReqMsg());
            }
        } else {
            ctx.fireUserEventTriggered(evt);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ProtocolMessage message = (ProtocolMessage) msg;
        final MessageTypeEnum msgType = message.getHeader().getMsgType();
        lossConnectCount = 0;
        if(MessageTypeEnum.HEARTBEAT_REQ.equals(msgType)) {
            LOGGER.info("receive heartbeat req.");
            ctx.writeAndFlush(buildHtRespMsg());
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private ProtocolMessage buildHtReqMsg() {
        HeartbeatReqBody htReqBody = new HeartbeatReqBody();

        return ProtocolMessage.createMsgOfEncode(htReqBody);
    }

    private ProtocolMessage buildHtRespMsg() {
        HeartbeatRespBody htRespBody = new HeartbeatRespBody();

        return ProtocolMessage.createMsgOfEncode(htRespBody);
    }
}
