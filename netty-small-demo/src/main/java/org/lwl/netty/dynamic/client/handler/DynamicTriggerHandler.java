package org.lwl.netty.dynamic.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.dynamic.client.DynamicTriggerEvent;


/**
 * @author thinking_fioa
 * @createTime 2018/8/6
 * @description 动态添加Handler
 */


public class DynamicTriggerHandler extends ChannelInboundHandlerAdapter{

    private static final Logger LOGGER = LogManager.getLogger(DynamicTriggerHandler.class);

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof DynamicTriggerEvent) {
            DynamicTriggerEvent triggerEvent = (DynamicTriggerEvent)evt;
            LOGGER.info("trigger DynamicTrigger {}", triggerEvent);
            triggerEvent.trigger(ctx, DynamicTriggerEvent.class);
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        LOGGER.error("happen unknown exception.", cause);
    }
}
