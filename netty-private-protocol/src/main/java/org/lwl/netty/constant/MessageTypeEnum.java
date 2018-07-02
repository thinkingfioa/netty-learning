package org.lwl.netty.constant;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @author thinking_fioa
 * @createTime 2018/4/21
 * @description 定义消息传输中消息类型常量
 */


public enum MessageTypeEnum {

    /**
     * Unknown 消息类型
     */
    UNKNOWN((byte)0, "unknown"),

    /**
     * 登陆消息
     */
    LOGIN_REQ((byte)1, "loginReq"),

    /**
     * 登陆响应
     */
    LOGIN_RESP((byte)2, "loginResp"),

    /**
     * 注销
     */
    LOGOUT((byte)3, "logout"),

    /**
     * 心跳请求
     */
    HEARTBEAT_REQ((byte)4, "heartbeatReq"),

    /**
     * 心跳响应
     */
    HEARTBEAT_RESP((byte)5, "heartbeatResp"),

    /**
     * 协议请求
     */
    PROTOCOL_SUB((byte)6, "protocolSub"),

    /**
     * 协议数据消息
     */
    PROTOCOL_DATA((byte)7, "protocolData")
    ;

    MessageTypeEnum(final byte type, final String desc) {
        this.msgType = type;
        this.desc = desc;
    }

    private final byte msgType;

    private final String desc;

    /**
     * 根据msgType类型，获取{@code MessageTypeEnum}<br>
     * 使用Hashmap缓存，提高缓存
     * @param msgType
     * @return
     */
    public static MessageTypeEnum getMsgTypeEnum(final Byte msgType) {
        if(null == msgType ) {
            return MessageTypeEnum.UNKNOWN;
        }


        return MessageTypeHolder.getMsgTypeEnum(msgType);
    }

    public byte getMsgType() {
        return msgType;
    }

    @Override
    public String toString() {
        return desc;
    }

    private static class MessageTypeHolder {
        private static Map<Byte, MessageTypeEnum> messageTypeMap;
        static {
            messageTypeMap = new HashMap<>();
            for(MessageTypeEnum type: MessageTypeEnum.values()) {
                messageTypeMap.put(type.getMsgType(), type);
            }
        }

        public static MessageTypeEnum getMsgTypeEnum(Byte key) {
            if(messageTypeMap.containsKey(key)) {
                return messageTypeMap.get(key);
            }
            return MessageTypeEnum.UNKNOWN;
        }
    }
}
