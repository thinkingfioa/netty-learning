package org.lwl.netty.dynamic.codec.serialize;

import io.netty.buffer.ByteBuf;
import org.lwl.netty.dynamic.message.Body;

/**
 * @author thinking_fioa
 * @createTime 2018/7/29
 * @description
 */


public interface IBodySerializer<T> {
    public void serialize(ByteBuf outByteBuf, Body body) throws Exception;

    public T deserialize(ByteBuf inByteBuf) throws Exception;
}
