package org.lwl.netty.dynamic.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.dynamic.DynamicConfig;
import org.lwl.netty.dynamic.DynamicMsgType;
import org.lwl.netty.dynamic.message.DynamicMessage;
import org.lwl.netty.dynamic.message.body.LoginReqBody;
import org.lwl.netty.dynamic.message.body.LoginRespBody;


/**
 * @author thinking_fioa
 * @createTime 2018/8/12
 * @description
 */


public class LoginRespHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LogManager.getLogger(LoginRespHandler.class);

    private final String USERNAME = DynamicConfig.getUserName();
    private final String PASSWD = DynamicConfig.getPasswd();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        DynamicMessage message = (DynamicMessage) msg;
        final DynamicMsgType msgType = message.getHeader().getMsgType();
        if(DynamicMsgType.LOGIN_REQ.equals(msgType)) {
            LOGGER.info("receive login req.");
            LoginReqBody reqBody = (LoginReqBody)message.getBody();
            if(checkPermission(reqBody)) {
                LOGGER.info("user check pass. login resp sent.");
                ctx.channel().writeAndFlush(buildLoginResp());
            }
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private boolean checkPermission(LoginReqBody reqBody) {
        final String userName  = reqBody.getUserName();
        final String passwd = reqBody.getPassword();

        return USERNAME.equals(userName) && PASSWD.equals(passwd);
    }

    private DynamicMessage buildLoginResp() {
        LoginRespBody respBody = new LoginRespBody();

        return DynamicMessage.createMsgOfEncode(respBody);
    }
}
