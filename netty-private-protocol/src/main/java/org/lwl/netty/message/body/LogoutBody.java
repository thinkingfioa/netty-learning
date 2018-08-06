package org.lwl.netty.message.body;

import org.lwl.netty.constant.MessageTypeEnum;
import org.lwl.netty.message.Body;

/**
 * @author thinking_fioa
 * @createTime 2018/5/9
 * @description 注销消息，目前没有用到
 */


public class LogoutBody extends Body{

    @Override
    public MessageTypeEnum msgType() {
        return MessageTypeEnum.LOGOUT;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LogoutBody []");
        return sb.toString();
    }
}
