package org.lwl.netty.chapter.one;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @author thinking_fioa
 * @createTime 2018/5/16
 * @description 代码清单1-3/1-4。 连接建立+回调函数
 */


public class ConnectExample {
    private static final Channel CHANNEL_FROM_SOMEWHERE = new NioSocketChannel();

    public static void connect() {
        Channel channel = CHANNEL_FROM_SOMEWHERE;

        ChannelFuture future = channel.connect(new InetSocketAddress("127.0.0.1", 9080));
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if(future.isSuccess()) {
                    ByteBuf buf = Unpooled.copiedBuffer("hello", Charset.defaultCharset());
                    ChannelFuture wf = future.channel().writeAndFlush(buf);
                    // ...
                } else {
                    future.cause().printStackTrace();
                }
            }
        });
    }
}
