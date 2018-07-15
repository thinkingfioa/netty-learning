package org.lwl.netty.server.handler.other;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.client.handler.other.LoginReqHandler;
import org.lwl.netty.constant.MessageTypeEnum;
import org.lwl.netty.message.ProtocolMessage;
import org.lwl.netty.message.body.ProtocolDataBody;

/**
 * @author thinking_fioa
 * @createTime 2018/4/21
 * @description 发送订阅消息
 */

public class ProtocolMsgSendHandler extends ChannelInboundHandlerAdapter{
    private static final Logger LOGGER = LogManager.getLogger(ProtocolMsgSendHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ProtocolMessage message = (ProtocolMessage) msg;
        final MessageTypeEnum msgType = message.getHeader().getMsgType();
        if(MessageTypeEnum.PROTOCOL_SUB.equals(msgType)) {
            LOGGER.info("receive msgSub message.");
            sendPrtcData();
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private void sendPrtcData() {
        // TODO 提供for循环多次发送，比较各种编码器性能
        buildPrctDataMsg();
    }

    private ProtocolMessage buildPrctDataMsg() {
        ProtocolDataBody dataBody = new ProtocolDataBody();
        //TODO:: 补充属性
        return ProtocolMessage.createMsgOfEncode(dataBody);
    }
}
