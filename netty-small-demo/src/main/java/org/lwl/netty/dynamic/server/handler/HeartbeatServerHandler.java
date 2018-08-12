package org.lwl.netty.dynamic.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.dynamic.DynamicConfig;
import org.lwl.netty.dynamic.DynamicMsgType;
import org.lwl.netty.dynamic.message.DynamicMessage;

/**
 * @author thinking_fioa
 * @createTime 2018/8/12
 * @description
 */


public class HeartbeatServerHandler extends ChannelInboundHandlerAdapter{
    private static final Logger LOGGER = LogManager.getLogger(HeartbeatServerHandler.class);
    private int lossConnectTime = 0;

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent)evt;
            lossConnectTime ++;
            if(event.state() == IdleState.READER_IDLE) {
                if(lossConnectTime > DynamicConfig.getHtMultiple()) {
                    LOGGER.error("heartbeat timeout. close channel {}", ctx.channel().remoteAddress());
                    ctx.close();
                    return;
                }
            } else if(event.state() == IdleState.WRITER_IDLE) {
                //TODO Write heartbeat msg
            }
        } else {
            ctx.fireUserEventTriggered(evt);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        DynamicMessage message = (DynamicMessage) msg;
        final DynamicMsgType msgType = message.getHeader().getMsgType();
        lossConnectTime = 0;
        if(DynamicMsgType.HEARTBEAT_RESP.equals(msgType)) {
            LOGGER.debug("receive heartbeat resp");
            return;
        } else {
            ctx.fireChannelRead(msg);
        }
    }

}
