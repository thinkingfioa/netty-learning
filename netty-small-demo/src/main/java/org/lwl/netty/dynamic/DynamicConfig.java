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
        return 3;
    }

    public static int getPort() {
        return 8989;
    }

    public static long getHtInterval() {
        return 5;
    }

    public static int getMaxFramelength() {
        //TODO:
        return -1;
    }

    public static int getLengthFieldOffset() {
        //TODO:
        return -1;
    }

    public static int getLengthfieldLength() {
        //TODO:
        return -1;
    }

    public static int getLengthAdjustment() {
        //TODO:
        return -1;
    }

    public static int getInitialBytesToStrip() {
        //TODO:
        return -1;
    }


}
