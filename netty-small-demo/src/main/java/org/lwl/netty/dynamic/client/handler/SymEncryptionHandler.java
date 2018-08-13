package org.lwl.netty.dynamic.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.dynamic.DynamicConfig;
import org.lwl.netty.dynamic.DynamicMsgType;
import org.lwl.netty.dynamic.client.DynamicTriggerEvent;
import org.lwl.netty.dynamic.message.DynamicMessage;
import org.lwl.netty.dynamic.message.body.SymEncryption;

/**
 * @author thinking_fioa
 * @createTime 2018/7/28
 * @description 发送Client端支持的对称加密方案
 */


public class SymEncryptionHandler extends ChannelInboundHandlerAdapter implements ITriggerHandler{
    private static final Logger LOGGER = LogManager.getLogger(SymEncryptionHandler.class);

    private static final String SYM_ENCRYPTION = DynamicConfig.getSymEncryption();

    @Override
    public void launch(ChannelHandlerContext ctx) {
        LOGGER.info("symEncryption sent.");
        ctx.writeAndFlush(buildSymEncryption());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        DynamicMessage message = (DynamicMessage) msg;
        final DynamicMsgType msgType = message.getHeader().getMsgType();
        if(DynamicMsgType.SYM_ENCRYPTION.equals(msgType)) {
            // 客户端接收到symEncryption消息后，启动随机数加密Handler
            LOGGER.info("receive symEncryption resp. launch randomCode.");
            ctx.fireUserEventTriggered(DynamicTriggerEvent.RANDOM_CODE_EVENT);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    public DynamicMessage buildSymEncryption() {
        SymEncryption symEncryption = new SymEncryption();
        symEncryption.setSymEncryptionMethod(SYM_ENCRYPTION);

        return DynamicMessage.createMsgOfEncode(symEncryption);
    }
}
