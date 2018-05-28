package org.lwl.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.constant.ProtocolDataType;
import org.lwl.netty.message.ProtocolMessage;
import org.lwl.netty.message.body.LoginReqBody;
import org.lwl.netty.message.body.ProtocolSubBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author thinking_fioa
 * @createTime 2018/5/24
 * @description Protocol消息订阅和接收服务
 */

public class ProtocolMsgSubHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LogManager.getLogger(ProtocolMsgSubHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ProtocolMessage ptclSubMsg = buildPtclSubMsg();
        LOGGER.info("MsgSub active. protocl msg sub sent");
        ctx.writeAndFlush(ptclSubMsg);
    }

    private ProtocolMessage buildPtclSubMsg() {
        ProtocolSubBody subBody = new ProtocolSubBody();
        List<ProtocolDataType> dataTypeList = new ArrayList<ProtocolDataType>();
        dataTypeList.add(ProtocolDataType.NEWS);
        dataTypeList.add(ProtocolDataType.SPORTS);
        dataTypeList.add(ProtocolDataType.ENTERTAINMENT);

        return ProtocolMessage.createMsgOfEncode(subBody);
    }
}
