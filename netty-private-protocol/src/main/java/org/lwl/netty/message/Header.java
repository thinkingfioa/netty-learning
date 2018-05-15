package org.lwl.netty.message;

import java.util.HashMap;
import java.util.Map;

/**
 * @author thinking_fioa
 * @createTime 2018/4/21
 * @description 消息头
 */


public class Header {
    private int msgLen;

    private long sessionID;

    private String msgType;

    private String senderName;

    private short flag;

    private byte oneByte;

    private Map<String, Object> attachment = new HashMap<String, Object>();

    public int getMsgLen() {
        return msgLen;
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

    public void setMsgLen(int msgLen) {
        this.msgLen = msgLen;
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
        sb.append("Header [");
        sb.append(", msgLen=").append(msgLen);
        sb.append(", sessionID=").append(sessionID);
        sb.append(", msgType=").append(msgType);
        sb.append(", senderName=").append(senderName);
        sb.append(", flag=").append(flag);
        sb.append(", oneByte=").append(oneByte);
        sb.append(", attachment").append(attachment).append("]");

        return sb.toString();
    }
}
