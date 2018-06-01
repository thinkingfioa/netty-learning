package org.lwl.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;

/**
 * @author thinking_fioa
 * @createTime 2018/5/9
 * @description
 */


public interface IMessageCodecUtil<T> {

    void encode(ChannelHandlerContext ctx, ByteBuf outByteBuf, T object) throws Exception;

    T decode(ChannelHandlerContext ctx, ByteBuf inByteBuf) throws Exception;
}
