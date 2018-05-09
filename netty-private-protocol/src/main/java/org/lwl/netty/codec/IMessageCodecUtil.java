package org.lwl.netty.codec;

import io.netty.buffer.ByteBuf;

/**
 * @author thinking_fioa
 * @createTime 2018/5/9
 * @description
 */


public interface IMessageCodecUtil<T> {

    void encode(ByteBuf outByteBuf, T object);

    T decode(ByteBuf inByteBuf);
}
