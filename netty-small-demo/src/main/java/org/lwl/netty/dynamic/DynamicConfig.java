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

    public static int getPort() {
        return 8989;
    }

    public static long getHtInterval() {
        return 5;
    }

    public static int getMaxFramelength() {

    }

    public static int getLengthFieldOffset() {

    }

    public static int getLengthfieldLength() {

    }

    public static int getLengthAdjustment() {

    }

    public static int getInitialBytesToStrip() {

    }


}
