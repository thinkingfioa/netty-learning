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

    private static final String IP = "127.0.0.1";

    private static final int PORT = 9876;

    private static final String CODEC_TYPE = MessageCodecTypeEnum.KRYO.getCodecType();

    public static String getIp() {
        return IP;
    }

    public static int getPort() {
        return PORT;
    }

    public static String getIP() {
        return IP;
    }
}
