package org.lwl.netty.message.body;

import org.lwl.netty.constant.MessageTypeEnum;
import org.lwl.netty.message.Body;

/**
 * @author thinking_fioa
 * @createTime 2018/5/9
 * @description 登陆消息
 */


public class LoginRespBody extends Body{

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LoginRespBody []");
        return sb.toString();
    }

    @Override
    public MessageTypeEnum msgType() {
        return MessageTypeEnum.LOGIN_RESP;
    }
}
