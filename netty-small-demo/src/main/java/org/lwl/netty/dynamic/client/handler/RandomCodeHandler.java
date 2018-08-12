package org.lwl.netty.dynamic.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author thinking_fioa
 * @createTime 2018/7/28
 * @description 产生随机码，使用服务端密钥加密后，发送至服务器
 */


public class RandomCodeHandler extends ChannelInboundHandlerAdapter implements ITriggerHandler{
    @Override
    public void launch(ChannelHandlerContext ctx) {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

    }
}
