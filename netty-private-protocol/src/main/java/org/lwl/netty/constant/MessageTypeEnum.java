package org.lwl.netty.constant;

/**
 * @author thinking_fioa
 * @createTime 2018/4/21
 * @description 定义消息传输中消息类型常量
 */


public enum MessageTypeEnum {

    SERVICE_REFUSE("service_refuse"),

    SERVICE_OK("service_ok"),

    SERVICE_RESP("service_resp"),

    LOGIN_REQ("login_req"),

    LOGIN_RESP("login_resp"),

    HEARTBEAT_REQ("heartbeat_req"),

    HEARTBEAT_RESP("heartbeat_resp")
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
