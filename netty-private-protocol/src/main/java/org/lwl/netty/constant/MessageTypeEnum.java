package org.lwl.netty.constant;

/**
 * @author thinking_fioa
 * @createTime 2018/4/21
 * @description 定义消息传输中消息类型常量
 */


public enum MessageTypeEnum {

    /**
     * Unknown 消息类型
     */
    UNKNOWN("unknown"),

    /**
     * 登陆消息
     */
    LOGIN_REQ("login_req"),

    /**
     * 登陆响应
     */
    LOGIN_RESP("login_resp"),

    /**
     * 注销
     */
    LOGOUT("logout"),

    /**
     * 心跳请求
     */
    HEARTBEAT_REQ("heartbeat_req"),

    /**
     * 心跳响应
     */
    HEARTBEAT_RESP("heartbeat_resp"),

    /**
     * 协议请求
     */
    PORTOCOL_SUB("protocol_sub"),

    /**
     * 协议数据消息
     */
    PORTOCOL_DATA("protocol_data")
    ;

    MessageTypeEnum(final String msgType) {
        this.msgType = msgType;
    }

    private final String msgType;

    /**
     * 根据msgType类型，获取{@code MessageTypeEnum}
     * @param msgType
     * @return
     */
    public static MessageTypeEnum getMsgTypeEnum(final String msgType) {
        if(null == msgType || msgType.isEmpty()) {
            return null;
        }
        for(MessageTypeEnum typeEnum : MessageTypeEnum.values()) {
            if(typeEnum.getMsgType().equals(msgType)){
                return typeEnum;
            }
        }

        return null;
    }

    public String getMsgType() {
        return msgType;
    }
}
