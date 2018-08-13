package org.lwl.netty.dynamic.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.dynamic.DynamicMsgType;
import org.lwl.netty.dynamic.message.DynamicMessage;
import org.lwl.netty.dynamic.message.body.SymEncryption;

/**
 * @author thinking_fioa
 * @createTime 2018/8/12
 * @description
 */


public class SymEncryptionServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LogManager.getLogger(SymEncryptionServerHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        DynamicMessage message = (DynamicMessage) msg;
        final DynamicMsgType msgType = message.getHeader().getMsgType();
        if(DynamicMsgType.SYM_ENCRYPTION.equals(msgType)) {
            LOGGER.info("receive symEncryption req.");
            SymEncryption symBody = (SymEncryption)message.getBody();
            ctx.channel().writeAndFlush(buildSslBody(symBody.getSymEncryptionMethod()));
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private DynamicMessage buildSslBody(String symEncryptionMethod) {
        SymEncryption symBody = new SymEncryption();
        symBody.setSymEncryptionMethod(symEncryptionMethod);

        return DynamicMessage.createMsgOfEncode(symBody);
    }
}
