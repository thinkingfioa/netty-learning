package org.lwl.netty.start;

import org.lwl.netty.config.ProtocolConfig;
import org.lwl.netty.server.NettyServer;

import java.io.IOException;

/**
 * @author thinking_fioa
 * @createTime 2018/4/24
 * @description
 */


public class NettyServerStart {
    public static void main(String [] args) throws IOException {
        ProtocolConfig.init();
        new NettyServer().start();
    }
}
