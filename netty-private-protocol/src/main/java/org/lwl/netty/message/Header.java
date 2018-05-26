package org.lwl.netty.message;

import java.util.HashMap;
import java.util.Map;

/**
 * @author thinking_fioa
 * @createTime 2018/4/21
 * @description 消息头
 */


public class Header {
    /**
     * 发送pkg长度
     */
    private int msgLen;

    /**
     * 发送消息数目
     */
    private long msgNum;

    /**
     * 消息类型，{@code MessageTypeEnum}
     */
    private String msgType;

    /**
     * 消息时间，格式:
     */
    private String msgTime;

    /**
     * short类型占位符
     */
    private short flag;

    /**
     * Byte类型占位符
     */
    private byte oneByte;

    /**
     * 扩展字段，允许动态添加
     */
    private Map<String, Object> attachment = new HashMap<String, Object>();

    public int getMsgLen() {
        return msgLen;
    }

    public long getMsgNum() {
        return msgNum;
    }

    public void setMsgNum(long msgNum) {
        this.msgNum = msgNum;
    }

    public Map<String, Object> getAttachment() {
        return attachment;
    }

    public void setMsgLen(int msgLen) {
        this.msgLen = msgLen;
    }

    public void setAttachment(Map<String, Object> attachment) {
        this.attachment = attachment;
    }

    public short getFlag() {
        return flag;
    }

    public void setFlag(short flag) {
        this.flag = flag;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }

    public byte getOneByte() {
        return oneByte;
    }

    public void setOneByte(byte oneByte) {
        this.oneByte = oneByte;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Header [");
        sb.append(", msgLen=").append(msgLen);
        sb.append(", msgNum=").append(msgNum);
        sb.append(", msgType=").append(msgType);
        sb.append(", flag=").append(flag);
        sb.append(", oneByte=").append(oneByte);
        sb.append(", attachment").append(attachment).append("]");

        return sb.toString();
    }
}
