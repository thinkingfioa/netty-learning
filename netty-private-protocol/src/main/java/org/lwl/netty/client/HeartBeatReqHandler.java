package org.lwl.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author thinking_fioa
 * @createTime 2018/4/21
 * @description 心跳请求
 */


public class HeartBeatReqHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LogManager.getLogger(HeartBeatReqHandler.class);

    private int lossConnectTime = 0;

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object event)  throws Exception{
        if(event instanceof IdleStateEvent) {
            //TODO:: write, read, all
        } else {
            super.userEventTriggered(ctx, event);
        }
    }
}
