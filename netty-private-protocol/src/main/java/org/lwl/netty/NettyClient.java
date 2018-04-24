package org.lwl.netty;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.constant.ProtocolConfig;

import java.awt.*;

/**
 * @author thinking_fioa
 * @createTime 2018/4/21
 * @description
 */


public class NettyClient {
    private static final Logger LOGGER = LogManager.getLogger(NettyClient.class);

    public void connect(final String ip, final int port) throws InterruptedException {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new NettyServer.ChildChannelHandler());

            ChannelFuture future = bootstrap.connect(ip, port).sync();
            LOGGER.info(" connect success, ip:{}, port:{}", ip, port);
            future.channel().closeFuture().sync();
        } finally {
            // 客户端需要负责重连
            // TODO:: 线程池
        }
    }
}
