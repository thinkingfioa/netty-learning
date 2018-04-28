package org.lwl.netty.message;

import java.util.HashMap;
import java.util.Map;

/**
 * @author thinking_fioa
 * @createTime 2018/4/21
 * @description 消息头
 */


public class Header {
    private int crcCode = 0xabef0101;

    private int length;

    private long sessionID;

    private String msgType;

    private String senderName;

    private short flag;

    private byte oneByte;

    private Map<String, Object> attachment = new HashMap<String, Object>();

    public int getCrcCode() {
        return crcCode;
    }

    public int getLength() {
        return length;
    }

    public long getSessionID() {
        return sessionID;
    }

    public String getSenderName() {
        return senderName;
    }

    public Map<String, Object> getAttachment() {
        return attachment;
    }

    public void setCrcCode(int crcCode) {
        this.crcCode = crcCode;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setSessionID(long sessionID) {
        this.sessionID = sessionID;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
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

    public byte getOneByte() {
        return oneByte;
    }

    public void setOneByte(byte oneByte) {
        this.oneByte = oneByte;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Header [ ")
                .append("crcCode=").append(crcCode)
                .append(", length=").append(length)
                .append(", sessionID=").append(sessionID)
                .append(", msgType=").append(msgType)
                .append(", senderName=").append(senderName)
                .append(", flag=").append(flag)
                .append(", oneByte=").append(oneByte)
                .append(", attachment").append(attachment).append(" ]");

        return sb.toString();
    }
}
