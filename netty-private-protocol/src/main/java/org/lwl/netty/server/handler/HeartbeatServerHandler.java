package org.lwl.netty.server.handler;

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
 * @createTime 2018/4/21
 * @description 心跳回复的Handler
 */


public class HeartbeatServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LogManager.getLogger(HeartbeatServerHandler.class);

    private int lossConnectCount = 0;

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        if(evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent)evt;
            if(event.state() == IdleState.WRITER_IDLE) {
                lossConnectCount++;
                if(lossConnectCount >=5) {
                    // 5 次客户端都没有给心跳回复，则关闭连接
                    ctx.channel().close();
                    LOGGER.error("heartbeat timeout, close.");
                    return ;
                }
                // send heartbeat msg
                LOGGER.warn("heartbeat req msg sent.");
                ctx.writeAndFlush(buildHtReqMsg());
            }
        } else {
            ctx.fireUserEventTriggered(evt);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception  {
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
