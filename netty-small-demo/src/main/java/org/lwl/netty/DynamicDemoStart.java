package org.lwl.netty;

import org.lwl.netty.dynamic.client.DynamicClient;
import org.lwl.netty.dynamic.server.DynamicServer;

/**
 * @author thinking_fioa
 * @createTime 2018/7/11
 * @description 动态编排ChannelHandler的启动main
 */


public class DynamicDemoStart {
    public static void main() {
        // Dynamic服务端以单个线程启动
        new Thread(new Runnable() {
            @Override
            public void run() {
                new DynamicServer().start();
            }
        }).start();

        // Dynamic客户端以单个线程启动
        new Thread(new Runnable() {
            @Override
            public void run() {
                new DynamicClient().start();
            }
        }).start();
    }
}
