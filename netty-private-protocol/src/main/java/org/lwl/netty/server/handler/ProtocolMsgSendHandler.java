package org.lwl.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.client.handler.LoginReqHandler;
import org.lwl.netty.constant.MessageTypeEnum;
import org.lwl.netty.message.ProtocolMessage;
import org.lwl.netty.message.body.LoginReqBody;
import org.lwl.netty.message.body.ProtocolDataBody;

/**
 * @author thinking_fioa
 * @createTime 2018/4/21
 * @description 发送订阅消息
 */

public class ProtocolMsgSendHandler extends ChannelInboundHandlerAdapter{
    private static final Logger LOGGER = LogManager.getLogger(LoginReqHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ProtocolMessage message = (ProtocolMessage) msg;
        final String msgType = message.getHeader().getMsgType();
        if(MessageTypeEnum.PORTOCOL_SUB.getMsgType().equals(msgType)) {
            LOGGER.info("receive msgSub message.");
            sendPrtcData();
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private void sendPrtcData() {
        buildPrctDataMsg();
    }

    private ProtocolMessage buildPrctDataMsg() {
        ProtocolDataBody dataBody = new ProtocolDataBody();
        //TODO:: 补充属性
        return ProtocolMessage.createMsgOfEncode(dataBody);
    }
}
