package org.lwl.netty.dynamic.message.body;

import org.lwl.netty.dynamic.DynamicMsgType;
import org.lwl.netty.dynamic.message.Body;

/**
 * @author thinking_fioa
 * @createTime 2018/7/29
 * @description
 */


public class LogoutBody extends Body {
    @Override
    public DynamicMsgType msgType() {
        return DynamicMsgType.LOGOUT;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LogoutBody []");
        return sb.toString();
    }
}
