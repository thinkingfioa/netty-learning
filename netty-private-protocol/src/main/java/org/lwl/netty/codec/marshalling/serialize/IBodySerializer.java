package org.lwl.netty.codec.marshalling.serialize;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.lwl.netty.message.Body;

/**
 * @author thinking_fioa
 * @createTime 2018/5/15
 * @description
 */


public interface IBodySerializer<T> {

    public void serialize(ChannelHandlerContext ctx,  ByteBuf outByteBuf, Body body) throws Exception;

    public T deserialize(ChannelHandlerContext ctx, ByteBuf inByteBuf) throws Exception;
}
