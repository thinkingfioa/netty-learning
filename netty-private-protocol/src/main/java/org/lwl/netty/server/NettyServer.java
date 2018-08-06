package org.lwl.netty.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.codec.other.ProtocolDataDecoder;
import org.lwl.netty.codec.other.ProtocolDataEncoder;
import org.lwl.netty.config.ProtocolConfig;
import org.lwl.netty.server.handler.other.HeartbeatServerHandler;
import org.lwl.netty.server.handler.other.LoginRespHandler;
import org.lwl.netty.server.handler.ServerExceptionHandler;
import org.lwl.netty.util.concurrent.CustomThreadFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author thinking_fioa
 * @createTime 2018/4/21
 * @description Netty Server端
 */


public class NettyServer {
    private static final Logger LOGGER = LogManager.getLogger(NettyServer.class);

    private EventLoopGroup bossGroup = new NioEventLoopGroup();
    private EventLoopGroup workerGroup = new NioEventLoopGroup(2, new CustomThreadFactory("ServerWorkerThread", true));

    public void bind(int port) throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024);
        initChannel0(bootstrap);

        ChannelFuture cf = bootstrap.bind(port);
        LOGGER.info("***********************************************");
        LOGGER.info("Netty Server started at port:{}", port);
        LOGGER.info("***********************************************");
        cf.addListener((future) -> {
            if(future.isSuccess()) {
                LOGGER.info("netty server bind success.");
            } else {
                LOGGER.error("netty server bind fail.", future.cause());
            }
        });
    }

    public void start() {
        try {
            bind(ProtocolConfig.getPort());
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

    protected void initChannel0(ServerBootstrap bootstrap) {
        bootstrap.childHandler(new ChildChannelHandler());
    }

    private static class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            //            ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
            ch.pipeline().addLast(new ProtocolDataDecoder());
            ch.pipeline().addLast(new ProtocolDataEncoder());
            ch.pipeline().addLast(new IdleStateHandler(ProtocolConfig.getHeartbeatInterval(), ProtocolConfig.getHeartbeatInterval(), 0, TimeUnit.SECONDS));
            ch.pipeline().addLast(new LoginRespHandler());
            ch.pipeline().addLast(new HeartbeatServerHandler());
//            ch.pipeline().addLast(new ProtocolMsgSendHandler());
            ch.pipeline().addLast(new ServerExceptionHandler());
        }
    }
 }
