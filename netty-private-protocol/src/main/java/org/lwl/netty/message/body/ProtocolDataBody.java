package org.lwl.netty.message.body;

import org.lwl.netty.constant.MessageTypeEnum;
import org.lwl.netty.message.IBody;

/**
 * @author thinking_fioa
 * @createTime 2018/5/26
 * @description 协议订阅body，客户端发送该订阅消息
 */


public class ProtocolDataBody implements IBody{

    @Override
    public MessageTypeEnum msgType() {
        return MessageTypeEnum.PORTOCOL_DATA;
    }

}
