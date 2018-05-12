package org.lwl.netty.client;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author thinking_fioa
 * @createTime 2018/5/12
 * @description
 */


public class handler {
    /**
     * @author thinking_fioa
     * @createTime 2018/4/21
     * @description 心跳请求
     */


    public static class HeartBeatReqHandler extends ChannelInboundHandlerAdapter {
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

    /**
     * @author thinking_fioa
     * @createTime 2018/5/9
     * @description
     */


    public static class InitClientHandler {
    }

    /**
     * @author thinking_fioa
     * @createTime 2018/4/21
     * @description 登陆请求
     */


    public static class LoginReqHandler extends ChannelHandlerAdapter {
    }

    /**
     * @author thinking_fioa
     * @createTime 2018/4/21
     * @description 协议数据发送
     */


    public static class ProtocolDataSendHandler extends ChannelHandlerAdapter {
    }
}
