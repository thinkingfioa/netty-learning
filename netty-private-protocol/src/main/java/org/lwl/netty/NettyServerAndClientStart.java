package org.lwl.netty;

import org.lwl.netty.client.NettyClientAdapter;
import org.lwl.netty.server.NettyServer;

/**
 * @author thinking_fioa
 * @createTime 2018/4/22
 * @description 分别以线程的方式启动NettyClient和NettyServer
 */


public class NettyServerAndClientStart {



    public static void main(String [] args) {
        // 用线程启动
        // 启动 Server
        new Thread(() -> {
            new NettyServer().start();
        }).start();

        new Thread(() -> {
            new NettyClientAdapter().start();
        }).start();
    }
}