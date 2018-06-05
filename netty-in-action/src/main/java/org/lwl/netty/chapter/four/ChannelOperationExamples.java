package org.lwl.netty.chapter.four;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author thinking_fioa
 * @createTime 2018/6/5
 * @description 代码清单4-5和4-6
 */


public class ChannelOperationExamples {
    private static final Channel CHANNEL_FROM_SOMEWHERE = new NioSocketChannel();

    /**
     * 代码清单4-5  往Channel中写
     */
    public static void writingToChannel() {
        Channel channel = CHANNEL_FROM_SOMEWHERE;
        ByteBuf buf = Unpooled.copiedBuffer("your data", CharsetUtil.UTF_8);
        ChannelFuture cf = channel.writeAndFlush(buf);
        cf.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                if (future.isSuccess()) {
                    System.out.println("Write successful");
                } else {
                    System.err.println("Write error");
                    future.cause().printStackTrace();
                }
            }
        });
    }

    /**
     * 代码清单4-6  多个线程同时访问同一个Channel。
     * 1. Channel是线程安全。
     * 2. 消息将严格按照顺序发送
     */
    public static void writingToChannelFromManyThreads() {
        final Channel channel = CHANNEL_FROM_SOMEWHERE;
        final ByteBuf buf = Unpooled.copiedBuffer("your data", CharsetUtil.UTF_8);
        Runnable writer = new Runnable() {
            @Override
            public void run() {
                channel.write(buf.duplicate());
            }
        };
        Executor executor = Executors.newCachedThreadPool();

        // write in one thread
        executor.execute(writer);

        // write in another thread
        executor.execute(writer);
        //...
    }
}
