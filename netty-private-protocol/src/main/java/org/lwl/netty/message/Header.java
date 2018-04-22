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

    private byte type;

    private String senderName;

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

    public byte getType() {
        return type;
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

    public void setType(byte type) {
        this.type = type;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public void setAttachment(Map<String, Object> attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "Header{" +
                "crcCode=" + crcCode +
                ", length=" + length +
                ", sessionID=" + sessionID +
                ", type=" + type +
                ", senderName='" + senderName + '\'' +
                ", attachment=" + attachment +
                '}';
    }
}
