package org.lwl.netty.message.body;

import org.lwl.netty.constant.MessageTypeEnum;
import org.lwl.netty.message.Body;

/**
 * @author thinking_fioa
 * @createTime 2018/5/26
 * @description 协议订阅body，客户端发送该订阅消息
 */

public class ProtocolDataBody extends Body{

    /**
     * 频道拆分的总发送package数
     * 协议定义最大发送4K数据，但考虑到频道内容大于4K，可能多次发送，在接收方再将完整的频道内容拼接完整。
     */
    private long pkgSum;

    /**
     * 当前发送package序号
     */
    private long pkgSequenceNum;

    /**
     * package内容长度
     */
    private int contentLen;

    /**
     * 当前package序号的内容
     */
    private byte [] content;

    public long getPkgSum() {
        return pkgSum;
    }

    public void setPkgSum(long pkgSum) {
        this.pkgSum = pkgSum;
    }

    public long getPkgSequenceNum() {
        return pkgSequenceNum;
    }

    public void setPkgSequenceNum(long pkgSequenceNum) {
        this.pkgSequenceNum = pkgSequenceNum;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public int getContentLen() {
        return contentLen;
    }

    public void setContentLen(int contentLen) {
        this.contentLen = contentLen;
    }

    @Override
    public MessageTypeEnum msgType() {
        return MessageTypeEnum.PROTOCOL_DATA;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProtocolSubBody [");
        sb.append("pkgSum=").append(pkgSum);
        sb.append("pkgSequenceNum=").append(pkgSequenceNum);
        sb.append("contentLen=").append(contentLen);
        if(null != content) {
            sb.append("contentLen=").append(content.length).append("]");
        } else {
            sb.append("content=").append("null").append("]");
        }

        return sb.toString();
    }


}
