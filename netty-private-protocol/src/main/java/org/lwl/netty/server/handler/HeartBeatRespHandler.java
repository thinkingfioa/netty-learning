package org.lwl.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.constant.MessageTypeEnum;
import org.lwl.netty.message.ProtocolMessage;

/**
 * @author thinking_fioa
 * @createTime 2018/4/21
 * @description 心跳回复的Handler
 */


public class HeartBeatRespHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LogManager.getLogger(HeartBeatRespHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception  {
        ProtocolMessage message = (ProtocolMessage) msg;
        final String msgType = message.getHeader().getMsgType();
        if(MessageTypeEnum.HEARTBEAT_REQ.getMsgType().equals(msgType)) {
            // 接收到心跳消息
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    public ProtocolMessage buildHeartBestResp() {
        return null;
    }
}
