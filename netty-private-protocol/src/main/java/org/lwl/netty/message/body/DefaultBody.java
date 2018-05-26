package org.lwl.netty.message.body;

import org.lwl.netty.constant.MessageTypeEnum;
import org.lwl.netty.message.IBody;

import java.util.function.IntBinaryOperator;

/**
 * @author thinking_fioa
 * @createTime 2018/5/26
 * @description 缺省消息
 */


public class DefaultBody implements IBody{
    @Override
    public MessageTypeEnum msgType() {
        return MessageTypeEnum.UNKNOWN;
    }
}
