package org.lwl.netty.dynamic.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.dynamic.DynamicConfig;
import org.lwl.netty.dynamic.DynamicMsgType;
import org.lwl.netty.dynamic.client.DynamicTriggerEvent;
import org.lwl.netty.dynamic.message.DynamicMessage;
import org.lwl.netty.dynamic.message.body.SslBody;

/**
 * @author thinking_fioa
 * @createTime 2018/7/28
 * @description 发送Client端的SSL版本信息
 */


public class SslHandler extends ChannelInboundHandlerAdapter implements ITriggerHandler{
    private static final Logger LOGGER = LogManager.getLogger(SslHandler.class);

    private static final String SSL_VERSION = DynamicConfig.getSslVersion();

    @Override
    public void launch(ChannelHandlerContext ctx) {
        LOGGER.info("ssl sent.");
        ctx.writeAndFlush(buildSslBody());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        DynamicMessage message = (DynamicMessage) msg;
        final DynamicMsgType msgType = message.getHeader().getMsgType();
        if(DynamicMsgType.SSL.equals(msgType)) {
            // 客户端接收到ssl消息后，启动对称加密Handler
            LOGGER.info("receive ssl resp. launch symEncryption.");
            ctx.fireUserEventTriggered(DynamicTriggerEvent.SYM_ENCRYPTION_EVENT);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private DynamicMessage buildSslBody() {
        SslBody sslBody = new SslBody();
        sslBody.setSslVersion(SSL_VERSION);

        return DynamicMessage.createMsgOfEncode(sslBody);
    }
}
