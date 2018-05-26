package org.lwl.netty.codec.marshalling.serialize;

import io.netty.buffer.ByteBuf;
import org.lwl.netty.message.Header;
import org.lwl.netty.message.IBody;
import org.lwl.netty.message.Tail;
import org.lwl.netty.message.body.DefaultBody;

/**
 * @author thinking_fioa
 * @createTime 2018/5/15
 * @description
 */


public interface IBodySerializer<T> {

    public void serialize(ByteBuf outByteBuf, T body);

    public T deserialize(ByteBuf inByteBuf);
}
