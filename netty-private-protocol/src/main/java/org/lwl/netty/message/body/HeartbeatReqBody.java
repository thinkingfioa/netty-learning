package org.lwl.netty.message.body;

import org.lwl.netty.constant.MessageTypeEnum;
import org.lwl.netty.message.IBody;

/**
 * @author thinking_fioa
 * @createTime 2018/5/26
 * @description 心跳数据
 */


public class HeartbeatReqBody implements IBody{
    // nothing

    @Override
    public MessageTypeEnum msgType() {
        return MessageTypeEnum.HEARTBEAT_REQ;
    }
}
