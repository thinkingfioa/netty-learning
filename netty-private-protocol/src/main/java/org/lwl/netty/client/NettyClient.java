package org.lwl.netty.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.client.handler.HeartBeatReqHandler;
import org.lwl.netty.client.handler.LoginReqHandler;
import org.lwl.netty.codec.ProtocolDataDecoder;
import org.lwl.netty.codec.ProtocolDataEncoder;

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
                .channel(NioSocketChannel.class)
                .handler(new ChildChannelHandler());
        ChannelFuture future = bootstrap.connect(ip, port).sync();
        LOGGER.info(" connect success, ip:{}, port:{}", ip, port);
        return future;
    }

    private static class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected  void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(new ProtocolDataDecoder());
            ch.pipeline().addLast(new ProtocolDataEncoder());
            ch.pipeline().addLast(new ReadTimeoutHandler(50));
            ch.pipeline().addLast(new LoginReqHandler());
            ch.pipeline().addLast(new HeartBeatReqHandler());
        }
    }
}
