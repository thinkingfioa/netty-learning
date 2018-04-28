package org.lwl.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author thinking_fioa
 * @createTime 2018/4/21
 * @description 心跳请求
 */


public class HeartBeatReqHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LogManager.getLogger(HeartBeatReqHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception{

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        return;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.error(cause);
    }
}
