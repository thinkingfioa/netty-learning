package org.lwl.netty.codec.marshalling.serialize;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.lwl.netty.message.Header;
import org.lwl.netty.message.IBody;
import org.lwl.netty.message.Tail;
import org.lwl.netty.message.body.DefaultBody;

import java.io.UnsupportedEncodingException;

/**
 * @author thinking_fioa
 * @createTime 2018/5/15
 * @description
 */


public interface IBodySerializer<T> {

    public void serialize(ChannelHandlerContext ctx,  ByteBuf outByteBuf, T body) throws Exception;

    public T deserialize(ChannelHandlerContext ctx, ByteBuf inByteBuf) throws Exception;
}
