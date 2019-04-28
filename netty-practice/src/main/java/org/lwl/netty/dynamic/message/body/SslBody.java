package org.lwl.netty.dynamic.message.body;

import org.lwl.netty.dynamic.DynamicMsgType;
import org.lwl.netty.dynamic.message.Body;

/**
 * @author thinking_fioa
 * @createTime 2018/7/28
 * @description
 */


public class SslBody extends Body {

    /**
     * ssl的版本
     */
    private String sslVersion;

    public String getSslVersion() {
        return sslVersion;
    }

    public void setSslVersion(String sslVersion) {
        this.sslVersion = sslVersion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SslBody [");
        sb.append("sslVersion=").append(sslVersion).append("]");

        return sb.toString();
    }

    @Override
    public DynamicMsgType msgType() {
        return DynamicMsgType.SSL;
    }
}
