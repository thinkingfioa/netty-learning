package org.lwl.netty.dynamic.message.body;

import org.lwl.netty.dynamic.DynamicMsgType;
import org.lwl.netty.dynamic.message.Body;

/**
 * @author thinking_fioa
 * @createTime 2018/5/9
 * @description 登陆消息
 */


public class LoginRespBody extends Body {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LoginRespBody []");
        return sb.toString();
    }

    @Override
    public DynamicMsgType msgType() {
        return DynamicMsgType.LOGIN_RESP;
    }
}
