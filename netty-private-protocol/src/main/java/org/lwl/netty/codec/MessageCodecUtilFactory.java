package org.lwl.netty.codec;

/**
 * @author thinking_fioa
 * @createTime 2018/5/12
 * @description
 */


public final class MessageCodecUtilFactory {

    private static final IMessageCodecUtil DEFAULT_CODEC_UTIL = null;

    private MessageCodecUtilFactory() {
        throw new IllegalAccessError("can not use constructor.");
    }

    public static IMessageCodecUtil createCodecUtil() {
        return DEFAULT_CODEC_UTIL;
    }


}
