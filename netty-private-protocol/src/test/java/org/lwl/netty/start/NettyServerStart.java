package org.lwl.netty.start;

import org.lwl.netty.client.NettyClientAdapter;
import org.lwl.netty.server.NettyServer;

/**
 * @author thinking_fioa
 * @createTime 2018/4/24
 * @description
 */


public class NettyServerStart {
    public static void main(String [] args) {
        new NettyClientAdapter().start();
    }
}
