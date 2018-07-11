package org.lwl.netty.client;

import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import org.lwl.netty.client.handler.ClientExceptionHandler;
import org.lwl.netty.client.handler.protobuf.HeartbeatClientHandler;
import org.lwl.netty.client.handler.protobuf.LoginReqHandler;
import org.lwl.netty.config.ProtocolConfig;

import java.util.concurrent.TimeUnit;

/**
 * @author thinking_fioa
 * @createTime 2018/7/8
 * @description
 */


public class ProtobufNettyClient extends NettyClient {

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        //      ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
        ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
        ch.pipeline().addLast(new ProtobufDecoder(org.lwl.netty.message.protobuf.ProtocolMessage.ProtocolMessageP.getDefaultInstance()));
        ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
        ch.pipeline().addLast(new ProtobufEncoder());
        ch.pipeline().addLast(new LoginReqHandler());
        ch.pipeline().addLast(new IdleStateHandler(ProtocolConfig.getHeartbeatInterval(), ProtocolConfig.getHeartbeatInterval(), 0, TimeUnit.SECONDS));
        ch.pipeline().addLast(new HeartbeatClientHandler());
        //            ch.pipeline().addLast(new ProtocolMsgSubHandler());
        ch.pipeline().addLast(new ClientExceptionHandler());
        //TODO:: 添加Handler
    }
}
