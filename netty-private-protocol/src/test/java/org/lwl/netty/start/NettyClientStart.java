package org.lwl.netty.start;

import org.lwl.netty.client.NettyClient;
import org.lwl.netty.constant.ProtocolConstant;

/**
 * @author thinking_fioa
 * @createTime 2018/4/24
 * @description
 */


public class NettyClientStart {

    private final static String IP = ProtocolConstant.getIP();
    private final static int PORT = ProtocolConstant.getPort();

    public static void main(String [] args) {
        try {
            new NettyClient().connect(IP, PORT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
