package org.lwl.netty.dynamic.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.core.CustomThreadFactory;
import org.lwl.netty.dynamic.DynamicConfig;
import org.lwl.netty.dynamic.codec.DynamicMsgDecoder;
import org.lwl.netty.dynamic.codec.DynamicMsgEncoder;
import org.lwl.netty.dynamic.server.handler.*;

import java.util.concurrent.TimeUnit;

/**
 * @author thinking_fioa
 * @createTime 2018/7/28
 * @description
 */


public class DynamicServer {
    private static final Logger LOGGER = LogManager.getLogger(DynamicServer.class);

    private EventLoopGroup bossGroup = new NioEventLoopGroup();
    private EventLoopGroup workerGroup = new NioEventLoopGroup(2, new CustomThreadFactory("DynamicServerWorker", true));

    public void bind(int port) throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChildChannelHandler());

        ChannelFuture cf = bootstrap.bind(port);
        LOGGER.info("***********************************************");
        LOGGER.info("Dynamic Server started at port:{}", port);
        LOGGER.info("***********************************************");
        cf.addListener((future) -> {
            if(future.isSuccess()) {
                LOGGER.info("dynamic server bind success.");
            } else {
                LOGGER.error("dynamic server bind fail.", future.cause());
            }
        });
    }

    public void start() {
        try {
            bind(DynamicConfig.getPort());
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
    }

    public void quit() {
        if(null != bossGroup) {
            bossGroup.shutdownGracefully();
        }
        if(null != workerGroup) {
            workerGroup.shutdownGracefully();
        }
    }

    private static class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(new DynamicMsgDecoder());
            ch.pipeline().addLast(new DynamicMsgEncoder());
            ch.pipeline().addLast(new IdleStateHandler(DynamicConfig.getHtInterval(), DynamicConfig.getHtInterval(), 0, TimeUnit.SECONDS));
            ch.pipeline().addLast(new HeartbeatServerHandler());
            ch.pipeline().addLast(new SslServerHandler());
            ch.pipeline().addLast(new SymEncryptionServerHandler());
            ch.pipeline().addLast(new LoginRespHandler());
            ch.pipeline().addLast(new ExceptionHanlder());
        }
    }
}
