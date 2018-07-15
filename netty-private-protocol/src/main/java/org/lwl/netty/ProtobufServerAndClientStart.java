package org.lwl.netty;

import org.lwl.netty.client.NettyClientAdapter;
import org.lwl.netty.client.ProtobufNettyClient;
import org.lwl.netty.config.ProtocolConfig;
import org.lwl.netty.server.NettyServer;
import org.lwl.netty.server.ProtobufNettyServer;

import java.io.IOException;

/**
 * @author thinking_fioa
 * @createTime 2018/7/8
 * @description 由于Protobuf使用的消息{@link org.lwl.netty.message.protobuf}非常特殊，所以，所有的传输机制
 * 都与编码方式:Marshalling和Kryo不同。单独一套。
 */

public class ProtobufServerAndClientStart {
    public static void main(String [] args) throws IOException {
        ProtocolConfig.init();
        new Thread(() -> {
            new ProtobufNettyServer().start();
        }).start();

        new Thread(() -> {
            new NettyClientAdapter(new ProtobufNettyClient()).start();
        }).start();
    }
}
