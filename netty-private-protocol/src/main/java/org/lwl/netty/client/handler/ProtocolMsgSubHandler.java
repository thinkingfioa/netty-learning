package org.lwl.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author thinking_fioa
 * @createTime 2018/5/24
 * @description Protocol消息订阅和接收服务
 */


public class ProtocolMsgSubHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // TODO:: channel active
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // TODO:: channel read
    }
}
