package org.lwl.netty.dynamic.message.body;

import org.lwl.netty.dynamic.DynamicMsgType;
import org.lwl.netty.dynamic.message.Body;

/**
 * @author thinking_fioa
 * @createTime 2018/5/26
 * @description 心跳数据
 */


public class HeartbeatReqBody extends Body {
    // nothing

    @Override
    public DynamicMsgType msgType() {
        return DynamicMsgType.HEARTBEAT_REQ;
    }

    @Override
    public String toString(){

        return "HeartbeatReqBody []";
    }
}
