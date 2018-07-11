package org.lwl.netty.client.handler.protobuf;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author thinking_fioa
 * @createTime 2018/7/11
 * @description 帮助Protobuf编码和解码工具类
 */


public final class ProtobufCodecHelper {

    private static final AtomicLong MSG_NUM = new AtomicLong(0);

    private ProtobufCodecHelper() {
        throw new IllegalAccessError("can not use constructor.");
    }

    public static long getMsgNum() {
        return MSG_NUM.incrementAndGet();
    }
}
