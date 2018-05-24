package org.lwl.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author thinking_fioa
 * @createTime 2018/4/21
 * @description 发送订阅消息
 */


public class ProtocolMsgSendHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // TODO:: channel read
    }
}
