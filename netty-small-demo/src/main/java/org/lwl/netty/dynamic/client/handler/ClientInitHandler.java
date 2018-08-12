package org.lwl.netty.dynamic.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.dynamic.client.DynamicTriggerEvent;

/**
 * @author thinking_fioa
 * @createTime 2018/7/28
 * @description
 */


public class ClientInitHandler extends ChannelInboundHandlerAdapter{
    private static final Logger LOGGER = LogManager.getLogger(ClientInitHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info("active channel {}", ctx.channel().remoteAddress());
        // TODO:: 启动ssl
//        ctx.fireUserEventTriggered(DynamicTriggerEvent.);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info("inactive channel {}", ctx.channel().remoteAddress());
        ctx.fireChannelInactive();
    }
}
