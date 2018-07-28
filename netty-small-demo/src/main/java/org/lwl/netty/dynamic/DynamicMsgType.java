package org.lwl.netty.dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * @author thinking_fioa
 * @createTime 2018/7/28
 * @description
 */


public enum DynamicMsgType {

    /**
     * Unknown 消息类型
     */
    UNKNOWN((byte)0, "unknown"),

    /**
     * SSL版本信息
     */
    SSL((byte)1, "SSL"),

    /**
     * 对称加密
     */
    SYM_ENCRYPTION((byte)2, "SymEncryption"),

    /**
     * 随机数生成密钥
     */
    RANDOM_CODE((byte)3, "RandomCode"),

    /**
     * 登陆消息
     */
    LOGIN_REQ((byte)4, "loginReq"),

    /**
     * 登陆响应
     */
    LOGIN_RESP((byte)5, "loginResp"),

    /**
     * 注销
     */
    LOGOUT((byte)6, "logout"),

    /**
     * 心跳请求
     */
    HEARTBEAT_REQ((byte)7, "heartbeatReq"),

    /**
     * 心跳响应
     */
    HEARTBEAT_RESP((byte)8, "heartbeatResp");


    private final byte msgType;
    private final String desc;

    DynamicMsgType(byte msgType, String desc) {
        this.msgType = msgType;
        this.desc = desc;
    }

    /**
     * 根据msgType类型，获取{@code DynamicMsgType}<br>
     * 使用Hashmap缓存，提高缓存
     * @param msgType
     * @return
     */
    public static DynamicMsgType getMsgTypeEnum(final Byte msgType) {
        if(null == msgType ) {
            return null;
        }

        return DynamicMsgTypeHolder.getMsgTypeEnum(msgType);
    }

    public byte getMsgType() {
        return msgType;
    }

    @Override
    public String toString() {
        return desc;
    }

    private static class DynamicMsgTypeHolder {
        private static Map<Byte, DynamicMsgType> msgTypeMap;
        static {
            msgTypeMap = new HashMap<>();
            for(DynamicMsgType type: DynamicMsgType.values()) {
                msgTypeMap.put(type.getMsgType(), type);
            }
        }

        public static DynamicMsgType getMsgTypeEnum(Byte key) {
            if(msgTypeMap.containsKey(key)) {
                return msgTypeMap.get(key);
            }
            return null;
        }
    }
}
