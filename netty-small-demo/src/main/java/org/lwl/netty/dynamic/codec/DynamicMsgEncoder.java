package org.lwl.netty.dynamic.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.dynamic.message.DynamicMessage;
import org.lwl.netty.dynamic.message.Header;

import java.util.HashMap;
import java.util.Map;

/**
 * @author thinking_fioa
 * @createTime 2018/4/26
 * @description 编码器
 */


public class DynamicMsgEncoder extends MessageToByteEncoder<DynamicMessage> {
    private static final Logger LOGGER = LogManager.getLogger(DynamicMsgEncoder.class);

    private long msgNum = 1;

    @Override
    protected void encode(ChannelHandlerContext ctx, DynamicMessage dynamicMsg, ByteBuf outByteBuf) throws Exception {
        if(null == dynamicMsg || null == dynamicMsg.getBody()) {
            LOGGER.error("protocolMessage is null. refuse encode");

            return;
        }
        try {
            // 填写头协议
            fillInHeader(dynamicMsg);
            // TODO 解码

            LOGGER.info("--> encode msgType:{}, msLen: {}", dynamicMsg.getHeader().getMsgType(), outByteBuf.writerIndex());
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
}
