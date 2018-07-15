package org.lwl.netty.chapter.six;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author thinking_fioa
 * @createTime 2018/7/15
 * @description  代码清单6-13。添加ChannelFutureListener到ChannelFuture
 */


public class ChannelFutures {
    public static void addingChannelFutureListener(ChannelHandlerContext ctx){
        Channel channel = ctx.channel();
        ByteBuf someMessage = Unpooled.buffer();
        //...
        io.netty.channel.ChannelFuture future = channel.write(someMessage);
        future.addListener((ChannelFuture f) -> {
            if(f.isSuccess()) {
                // operation success.
                System.out.println("success.");
            }else {
                // operation fail
                f.cause().printStackTrace();
            }
        });
    }
}
