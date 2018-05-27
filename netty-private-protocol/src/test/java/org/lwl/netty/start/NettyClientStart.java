package org.lwl.netty.start;

import org.lwl.netty.client.NettyClient;
import org.lwl.netty.client.NettyClientAdapter;
import org.lwl.netty.config.ProtocolConfig;
import org.lwl.netty.constant.ProtocolConstant;

/**
 * @author thinking_fioa
 * @createTime 2018/4/24
 * @description
 */


public class NettyClientStart {

    public static void main(String [] args) {
        new NettyClientAdapter().start();
    }
}
