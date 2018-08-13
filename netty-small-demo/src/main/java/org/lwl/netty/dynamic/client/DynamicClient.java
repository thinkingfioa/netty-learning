package org.lwl.netty.dynamic.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.core.CustomThreadFactory;
import org.lwl.netty.dynamic.client.handler.ClientInitHandler;
import org.lwl.netty.dynamic.client.handler.DynamicTriggerHandler;
import org.lwl.netty.dynamic.codec.DynamicMsgDecoder;
import org.lwl.netty.dynamic.codec.DynamicMsgEncoder;

/**
 * @author thinking_fioa
 * @createTime 2018/7/28
 * @description
 */


public class DynamicClient {
    private static final Logger LOGGER = LogManager.getLogger(DynamicClient.class);

    private EventLoopGroup group = new NioEventLoopGroup(1, new CustomThreadFactory("DynamicClientGroup", true));

    private void connect(String ip, int port) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class)
                .handler(new ChildChannelHandler());


    }

    private static class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            // 初始化只有4个Handler，其他的Handler动态加入到Pipeline
            ch.pipeline().addLast(new DynamicMsgDecoder());
            ch.pipeline().addLast(new DynamicMsgEncoder());
            ch.pipeline().addLast(new ClientInitHandler());
            ch.pipeline().addLast(new DynamicTriggerHandler());
        }
    }
}
