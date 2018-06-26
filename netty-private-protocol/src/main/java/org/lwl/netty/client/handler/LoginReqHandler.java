package org.lwl.netty.client.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.config.ProtocolConfig;
import org.lwl.netty.constant.MessageTypeEnum;
import org.lwl.netty.message.ProtocolMessage;
import org.lwl.netty.message.body.LoginReqBody;

/**
 * @author thinking_fioa
 * @createTime 2018/5/24
 * @description 登陆请求
 */

@ChannelHandler.Sharable
public class LoginReqHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LogManager.getLogger(LoginReqHandler.class);

    private static final String USERNAME = ProtocolConfig.getUserName();
    private static final String PASSWORD = ProtocolConfig.getPassword();

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ProtocolMessage loginMsg = buildLoginReqMsg();
        LOGGER.info("channel active. login req sent.");
        ctx.writeAndFlush(loginMsg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ProtocolMessage message = (ProtocolMessage) msg;
        final MessageTypeEnum msgType = message.getHeader().getMsgType();
        if(MessageTypeEnum.LOGIN_RESP.equals(msgType)) {
            LOGGER.info("receive login resp.");
            ctx.fireChannelActive();
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private ProtocolMessage buildLoginReqMsg() {
        LoginReqBody loginReqBody = new LoginReqBody();
        loginReqBody.setUserName(USERNAME);
        loginReqBody.setPassword(PASSWORD);

        return ProtocolMessage.createMsgOfEncode(loginReqBody);
    }
}
