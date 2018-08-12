package org.lwl.netty.dynamic.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author thinking_fioa
 * @createTime 2018/7/28
 * @description 发送Client端的SSL版本信息
 */


public class SslHandler extends ChannelInboundHandlerAdapter implements ITriggerHandler{

    @Override
    public void launch(ChannelHandlerContext ctx) {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

    }
}
