package org.lwl.netty.dynamic.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.dynamic.DynamicMsgType;
import org.lwl.netty.dynamic.message.DynamicMessage;
import org.lwl.netty.dynamic.message.body.SslBody;

/**
 * @author thinking_fioa
 * @createTime 2018/8/12
 * @description
 */


public class SslServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LogManager.getLogger(SslServerHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        DynamicMessage message = (DynamicMessage) msg;
        final DynamicMsgType msgType = message.getHeader().getMsgType();
        if(DynamicMsgType.SSL.equals(msgType)) {
            LOGGER.info("receive ssl req.");
            SslBody sslBody = (SslBody)message.getBody();
            ctx.channel().writeAndFlush(buildSslBody(sslBody.getSslVersion()));
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private DynamicMessage buildSslBody(String version) {
        SslBody sslBody = new SslBody();
        sslBody.setSslVersion(version);

        return DynamicMessage.createMsgOfEncode(sslBody);
    }

}
