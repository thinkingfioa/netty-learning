package org.lwl.netty.chapter.six;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @author thinking_fioa
 * @createTime 2018/7/15
 * @description 代码清单6-14，添加ChannelFutureListener到ChannelPromise
 */

public class OutboundExceptionHandler extends ChannelOutboundHandlerAdapter{

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        ctx.write(msg, promise);
        promise.addListener((ChannelFuture f) -> {
            if (f.isSuccess()) {
                // operation success.
                System.out.println("success.");
            }else {
                // operation fail
                f.cause().printStackTrace();
            }
        });
    }

}
