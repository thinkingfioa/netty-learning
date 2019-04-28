package org.lwl.netty.dynamic.codec.serialize;

import org.lwl.netty.dynamic.DynamicMsgType;
import org.lwl.netty.dynamic.codec.serialize.body.*;
import org.lwl.netty.dynamic.message.Body;

import java.util.EnumMap;

/**
 * @author thinking_fioa
 * @createTime 2018/7/29
 * @description
 */


public final class DynamicSerializerFactory {

    private DynamicSerializerFactory() {
        throw new IllegalAccessError("can not use constructor.");
    }

    private static EnumMap<DynamicMsgType, IBodySerializer<? extends Body>> bodySerializerMap = new EnumMap<>(DynamicMsgType.class);

    static {
        bodySerializerMap.put(DynamicMsgType.UNKNOWN, DefaultBodySerializer.getInstance());
        bodySerializerMap.put(DynamicMsgType.SSL, SslBodySerializer.getInstance());
        bodySerializerMap.put(DynamicMsgType.SYM_ENCRYPTION, SymEncryptionSerializer.getInstance());
        bodySerializerMap.put(DynamicMsgType.RANDOM_CODE, RandomCodeBodySerializer.getInstance());
        bodySerializerMap.put(DynamicMsgType.LOGIN_REQ, LoginReqBodySerializer.getInstance());
        bodySerializerMap.put(DynamicMsgType.LOGIN_RESP, LoginRespBodySerializer.getInstance());
        bodySerializerMap.put(DynamicMsgType.LOGOUT, LogoutBodySerializer.getInstance());
        bodySerializerMap.put(DynamicMsgType.HEARTBEAT_REQ, HtReqBodySerializer.getInstance());
        bodySerializerMap.put(DynamicMsgType.HEARTBEAT_RESP, HtRespBodySerializer.getInstance());
    }

    public static IBodySerializer<? extends Body> getBodySerializer(final DynamicMsgType msgType) {
        IBodySerializer<? extends Body> bodySerializer = bodySerializerMap.get(msgType);

        return null != bodySerializer ? bodySerializer: DefaultBodySerializer.getInstance();
    }
}
