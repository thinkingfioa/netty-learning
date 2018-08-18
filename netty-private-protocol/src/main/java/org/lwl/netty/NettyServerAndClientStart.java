package org.lwl.netty;

import org.lwl.netty.client.NettyClient;
import org.lwl.netty.client.NettyClientAdapter;
import org.lwl.netty.config.ProtocolConfig;
import org.lwl.netty.server.NettyServer;

import java.io.IOException;

/**
 * @author thinking_fioa
 * @createTime 2018/4/22
 * @description 分别以线程的方式启动NettyClient和NettyServer
 */


public class NettyServerAndClientStart {

    public static void main(String [] args) throws IOException, InterruptedException {
        ProtocolConfig.init();
        // 用线程启动
        // 启动 Server
        new Thread(() -> {
            new NettyServer().start();
        }).start();

        Thread.sleep(60000);

        new Thread(() -> {
            new NettyClientAdapter(new NettyClient()).start();
        }).start();
    }
}