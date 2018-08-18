package org.lwl.netty.dynamic;

/**
 * @author thinking_fioa
 * @createTime 2018/8/6
 * @description
 */


public class DynamicConfig {
    private DynamicConfig() {
        throw new UnsupportedOperationException("static class");
    }

    public static String getUserName() {
        return "thinking";
    }

    public static String getPasswd() {
        return "fioa";
    }

    public static String getSslVersion() {
        return "3.2.2";
    }

    public static String getSymEncryption() {
        return "AES";
    }

    public static int getHtMultiple() {
        return 6;
    }

    public static String getServerIp() {
        return "127.0.0.1";
    }

    public static int getPort() {
        return 8989;
    }

    public static long getHtInterval() {
        return 5;
    }

    public static int getMaxFramelength() {
        // 4K
        return 4 * 1024;
    }

    public static int getLengthFieldOffset() {
        return 0;
    }

    public static int getLengthfieldLength() {
        return 4;
    }

    public static int getLengthAdjustment() {
        return -4;
    }

    public static int getInitialBytesToStrip() {
        return 0;
    }


}
