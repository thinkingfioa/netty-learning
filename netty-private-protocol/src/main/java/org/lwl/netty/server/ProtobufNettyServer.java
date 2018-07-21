package org.lwl.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import org.lwl.netty.config.ProtocolConfig;
import org.lwl.netty.server.handler.ServerExceptionHandler;
import org.lwl.netty.server.handler.protobuf.HeartbeatServerHandler;
import org.lwl.netty.server.handler.protobuf.LoginRespHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author thinking_fioa
 * @createTime 2018/7/8
 * @description
 */


public class ProtobufNettyServer extends NettyServer {

    @Override
    public void initChannel0(ServerBootstrap bootstrap) {
        bootstrap.childHandler(new ProtobufChildChannelHandler());
    }

    private static class ProtobufChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        public void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
            ch.pipeline().addLast(new ProtobufDecoder(org.lwl.netty.message.protobuf.ProtocolMessage.ProtocolMessageP.getDefaultInstance()));
            ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
            ch.pipeline().addLast(new ProtobufEncoder());
            ch.pipeline().addLast(new IdleStateHandler(ProtocolConfig.getHeartbeatInterval(), ProtocolConfig.getHeartbeatInterval(), 0, TimeUnit.SECONDS));
            ch.pipeline().addLast(new LoginRespHandler());
            ch.pipeline().addLast(new HeartbeatServerHandler());
            ch.pipeline().addLast(new ServerExceptionHandler());
        }
    }

}
