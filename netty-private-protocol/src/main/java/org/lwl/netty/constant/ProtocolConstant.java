package org.lwl.netty.constant;

import org.lwl.netty.config.ProtocolConfig;

/**
 * @author thinking_fioa
 * @createTime 2018/4/22
 * @description 私有协议中配置的信息
 */


public final class ProtocolConstant {
    // can't use
    private ProtocolConstant() {
        throw new IllegalAccessError("static class, can not use constructor.");
    }

    /**
     * @link LengthFieldBasedFrameDecoder} 使用的参数.
     * 最大消息字节数。4K = 4 * 1024
     */
    private static final int MAX_FRAMELENGTH = ProtocolConfig.getPkgMaxLen();
    private static final int LENGTH_FIELD_OFFSET = 0;
    private static final int LENGTHFIELD_LENGTH = 4;
    private static final int LENGTH_ADJUSTMENT = -4;
    private static final int INITIAL_BYTES_TO_STRIP = 0;

    /**
     * 序列化/反序列化方式
     */
    private static final String CODEC_TYPE = MessageCodecTypeEnum.KRYO.getCodecType();

    public static int getMaxFramelength() {
        return MAX_FRAMELENGTH;
    }

    public static int getLengthFieldOffset() {
        return LENGTH_FIELD_OFFSET;
    }

    public static int getLengthfieldLength() {
        return LENGTHFIELD_LENGTH;
    }

    public static int getLengthAdjustment() {
        return LENGTH_ADJUSTMENT;
    }

    public static int getInitialBytesToStrip() {
        return INITIAL_BYTES_TO_STRIP;
    }

    public static String getCodecType() {
        return CODEC_TYPE;
    }

}
