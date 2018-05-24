package org.lwl.netty.client.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author thinking_fioa
 * @createTime 2018/5/24
 * @description 心跳
 */

@ChannelHandler.Sharable
public class HeartBeatReqHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object msg) {
        // TODO:: 心跳事件
    }
}
