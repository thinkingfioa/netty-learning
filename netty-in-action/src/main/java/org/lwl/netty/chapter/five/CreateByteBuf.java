package org.lwl.netty.chapter.five;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author thinking_fioa
 * @createTime 2018/6/24
 * @description
 */


public class CreateByteBuf {

    public void createByteBuf(ChannelHandlerContext ctx) {
        // 1. 通过Channel创建ByteBuf
        ByteBuf buf1 = ctx.channel().alloc().buffer();
        // 2. 通过ByteBufAllocator.DEFAULT创建
        ByteBuf buf2 =  ByteBufAllocator.DEFAULT.buffer();
        // 3. 通过Unpooled创建
        ByteBuf buf3 = Unpooled.buffer();
    }
}
