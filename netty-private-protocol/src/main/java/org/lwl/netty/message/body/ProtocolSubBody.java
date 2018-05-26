package org.lwl.netty.message.body;

import org.lwl.netty.constant.MessageTypeEnum;
import org.lwl.netty.message.IBody;

/**
 * @author thinking_fioa
 * @createTime 2018/5/26
 * @description 自定义协议数据消息。服务端根据客户端订阅的类型，发送具体对应的数据
 */


public class ProtocolSubBody implements IBody{


    @Override
    public MessageTypeEnum msgType() {
        return MessageTypeEnum.PORTOCOL_SUB;
    }

}
