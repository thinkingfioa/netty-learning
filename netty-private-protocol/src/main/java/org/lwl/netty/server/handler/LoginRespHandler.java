package org.lwl.netty.server.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.client.handler.LoginReqHandler;
import org.lwl.netty.config.ProtocolConfig;
import org.lwl.netty.constant.MessageTypeEnum;
import org.lwl.netty.message.ProtocolMessage;
import org.lwl.netty.message.body.LoginReqBody;
import org.lwl.netty.message.body.LoginRespBody;

/**
 * @author thinking_fioa
 * @createTime 2018/4/21
 * @description 登陆响应的Handler
 */


public class LoginRespHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LogManager.getLogger(LoginReqHandler.class);

    private static final String USERNAME = ProtocolConfig.getUserName();
    private static final String PASSWORD = ProtocolConfig.getPassword();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ProtocolMessage message = (ProtocolMessage) msg;
        final MessageTypeEnum msgType = message.getHeader().getMsgType();
        if(MessageTypeEnum.LOGIN_REQ.equals(msgType)) {
            LOGGER.info("receive login req.");
            LoginReqBody reqBody = (LoginReqBody) message.getBody();
            if(checkPermission(reqBody)) {
                LOGGER.info("user check pass. login resp sent.");
                ctx.writeAndFlush(buildLoginResp());
            }
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private boolean checkPermission(LoginReqBody reqBody) {
        final String userName  = reqBody.getUserName();
        final String passwd = reqBody.getPassword();

        return USERNAME.equals(userName) && PASSWORD.equals(passwd);
    }

    private ProtocolMessage buildLoginResp() {
        LoginRespBody respBody = new LoginRespBody();

        return ProtocolMessage.createMsgOfEncode(respBody);
    }
}
