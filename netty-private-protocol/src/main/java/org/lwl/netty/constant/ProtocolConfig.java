package org.lwl.netty.constant;

/**
 * @author thinking_fioa
 * @createTime 2018/4/22
 * @description 私有协议中配置的信息
 */


public final class ProtocolConfig {
    // can't use
    private ProtocolConfig() {
        throw new IllegalAccessError("static class, can not use constructor.");
    }

    private static String Ip = "127.0.0.1";
    private static int port = 9876;

    public static String getIp() {
        return Ip;
    }

    public static void setIp(String ip) {
        Ip = ip;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        ProtocolConfig.port = port;
    }
}
