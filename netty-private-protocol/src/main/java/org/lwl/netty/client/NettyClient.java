package org.lwl.netty.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.client.handler.ClientExceptionHandler;
import org.lwl.netty.client.handler.other.HeartbeatClientHandler;
import org.lwl.netty.client.handler.other.LoginReqHandler;
import org.lwl.netty.codec.other.ProtocolDataDecoder;
import org.lwl.netty.codec.other.ProtocolDataEncoder;
import org.lwl.netty.config.ProtocolConfig;

import java.util.concurrent.TimeUnit;

/**
 * @author thinking_fioa
 * @createTime 2018/4/21
 * @description Netty Client连接端
 */


public class NettyClient {
    private static final Logger LOGGER = LogManager.getLogger(NettyClient.class);

    public ChannelFuture connect(EventLoopGroup group, final String ip, final int port) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class);
        initChannel0(bootstrap);
        ChannelFuture future = bootstrap.connect(ip, port).sync();
        LOGGER.info(" connect success, ip:{}, port:{}", ip, port);
        return future;
    }

    /**
     * 字类可以覆盖写
     * @throws Exception
     */
    protected  void initChannel0(Bootstrap bootstrap) {
        bootstrap.handler(new ChildChannelHandler());
    }

    private static class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected  void initChannel(SocketChannel ch) throws Exception {
            //      ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
            ch.pipeline().addLast(new ProtocolDataDecoder());
            ch.pipeline().addLast(new ProtocolDataEncoder());
            ch.pipeline().addLast(new LoginReqHandler());
            ch.pipeline().addLast(new IdleStateHandler(ProtocolConfig.getHeartbeatInterval(), ProtocolConfig.getHeartbeatInterval(), 0, TimeUnit.SECONDS));
            ch.pipeline().addLast(new HeartbeatClientHandler());
//            ch.pipeline().addLast(new ProtocolMsgSubHandler());
            ch.pipeline().addLast(new ClientExceptionHandler());
        }
    }
}
