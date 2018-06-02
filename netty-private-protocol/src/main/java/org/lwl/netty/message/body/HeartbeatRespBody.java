package org.lwl.netty.message.body;

import org.lwl.netty.constant.MessageTypeEnum;
import org.lwl.netty.message.Body;

/**
 * @author thinking_fioa
 * @createTime 2018/5/26
 * @description 心跳数据
 */


public class HeartbeatRespBody extends Body {
    // nothing

    @Override
    public MessageTypeEnum msgType() {
        return MessageTypeEnum.HEARTBEAT_RESP;
    }

    @Override
    public String toString(){

        return "HeartbeatRespBody []";
    }

}
