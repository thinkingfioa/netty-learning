package org.lwl.netty.dynamic.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.core.CommonUtil;
import org.lwl.netty.dynamic.DynamicMsgType;
import org.lwl.netty.dynamic.codec.serialize.DynamicSerializerFactory;
import org.lwl.netty.dynamic.codec.serialize.HeaderSerializer;
import org.lwl.netty.dynamic.codec.serialize.IBodySerializer;
import org.lwl.netty.dynamic.codec.serialize.TailSerializer;
import org.lwl.netty.dynamic.message.Body;
import org.lwl.netty.dynamic.message.DynamicMessage;
import org.lwl.netty.dynamic.message.Header;
import org.lwl.netty.dynamic.message.Tail;

import java.util.HashMap;
import java.util.Map;

/**
 * @author thinking_fioa
 * @createTime 2018/4/26
 * @description 编码器
 */


public class DynamicMsgEncoder extends MessageToByteEncoder<DynamicMessage> {
    private static final Logger LOGGER = LogManager.getLogger(DynamicMsgEncoder.class);
    private final byte [] LENGTH_PLACEHOLDER = new byte[4];

    private long msgNum = 1;

    @Override
    protected void encode(ChannelHandlerContext ctx, DynamicMessage msg, ByteBuf outByteBuf) throws Exception {
        if(null == msg || null == msg.getBody()) {
            LOGGER.error("DynamicMessage is null. refuse encode");

            return;
        }
        try {
            // 填写头协议
            fillInHeader(msg);

            // 填入长度占位符
            outByteBuf.writeBytes(LENGTH_PLACEHOLDER);
            // Header
            HeaderSerializer.getInstance().serialize(outByteBuf, msg.getHeader());

            // Body
            IBodySerializer<? extends Body> bodySerializer = getBodySerializer(msg.getBody().msgType());
            bodySerializer.serialize(outByteBuf, msg.getBody());

            // 更新Header消息中的长度域字段
            int msgLen = outByteBuf.writerIndex() + Tail.byteSize();
            outByteBuf.setInt(0, msgLen);
            // Tail
            TailSerializer.getInstance().serialize( outByteBuf, msg.getTail());


            LOGGER.info("--> encode msgType:{}, msLen: {}", msg.getHeader().getMsgType(), outByteBuf.writerIndex());
        } catch(Throwable cause) {
            LOGGER.error("Encode error.", cause);
        }
    }

    private void fillInHeader(DynamicMessage dynamicMsg) {
        Header header = dynamicMsg.getHeader();
        header.setMsgNum(msgNum++);
        header.setMsgType(dynamicMsg.getBody().msgType());
        header.setMsgTime(CommonUtil.nowTime());
    }


    private boolean checkSumRight(ByteBuf inByteBuf, int length, int sendCheckSum) {
        int calCheckSum = CommonUtil.calCheckSum(inByteBuf, length);
        if(calCheckSum == sendCheckSum){
            return true;
        }

        return false;
    }

    private IBodySerializer<? extends Body> getBodySerializer(DynamicMsgType msgType) {
        return DynamicSerializerFactory.getBodySerializer(msgType);
    }
}
