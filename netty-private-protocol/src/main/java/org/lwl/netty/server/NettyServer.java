package org.lwl.netty.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.codec.ProtocolDataDecoder;
import org.lwl.netty.codec.ProtocolDataEncoder;
import org.lwl.netty.config.ProtocolConfig;
import org.lwl.netty.server.handler.HeartBeatRespHandler;
import org.lwl.netty.server.handler.LoginRespHandler;
import org.lwl.netty.util.concurrent.CustomThreadFactory;

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
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChildChannelHandler());

        ChannelFuture cf = bootstrap.bind(port);
        LOGGER.info("***********************************************");
        LOGGER.info("Netty Server started at port:{}", port);
        LOGGER.info("***********************************************");
        cf.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if(future.isSuccess()) {
                    LOGGER.info("netty server bind success.");
                } else {
                    LOGGER.error("netty server bind fail.", future.cause());
                }
            }
        });
    }

    public void start() {
        try {
            new NettyServer().bind(ProtocolConfig.getPort());
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

    public static class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            // TODO:: 动态事件编排
            socketChannel.pipeline().addLast(new ProtocolDataDecoder());
            socketChannel.pipeline().addLast(new ProtocolDataEncoder());
            socketChannel.pipeline().addLast(new ReadTimeoutHandler(50));
            socketChannel.pipeline().addLast(new HeartBeatRespHandler());
            socketChannel.pipeline().addLast(new LoginRespHandler());
        }
    }
}
