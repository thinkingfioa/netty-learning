package org.lwl.netty.dynamic.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author thinking_fioa
 * @createTime 2018/8/12
 * @description
 */


public class ExceptionHanlder extends ChannelInboundHandlerAdapter{
    private static final Logger LOGGER = LogManager.getLogger(ExceptionHanlder.class);

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        LOGGER.error("happen unknown exception.", cause);
    }
}
