package org.lwl.netty.dynamic.message;

import org.lwl.netty.dynamic.DynamicMsgType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author thinking_fioa
 * @createTime 2018/4/21
 * @description 消息头
 */


public class Header {
    /**
     * 发送消息数目
     */
    private long msgNum;

    /**
     * 消息类型，{@code DynamicMsgType}
     */
    private DynamicMsgType msgType;

    /**
     * 消息时间，格式:
     */
    private String msgTime;

    public long getMsgNum() {
        return msgNum;
    }

    public void setMsgNum(long msgNum) {
        this.msgNum = msgNum;
    }

    public DynamicMsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(DynamicMsgType msgType) {
        this.msgType = msgType;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Header [");
        sb.append("msgNum=").append(msgNum);
        sb.append(", msgType=").append(msgType).append("]");

        return sb.toString();
    }
}
