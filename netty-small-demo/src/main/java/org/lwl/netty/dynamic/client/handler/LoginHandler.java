package org.lwl.netty.dynamic.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.dynamic.DynamicConfig;
import org.lwl.netty.dynamic.DynamicMsgType;
import org.lwl.netty.dynamic.client.DynamicTriggerEvent;
import org.lwl.netty.dynamic.message.DynamicMessage;
import org.lwl.netty.dynamic.message.body.LoginReqBody;

/**
 * @author thinking_fioa
 * @createTime 2018/7/28
 * @description 发送登录请求
 */


public class LoginHandler extends ChannelInboundHandlerAdapter implements ITriggerHandler{
    private static final Logger LOGGER = LogManager.getLogger(LoginHandler.class);

    private final String USERNAME = DynamicConfig.getUserName();
    private final String PASSWD = DynamicConfig.getPasswd();

    @Override
    public void launch(ChannelHandlerContext ctx) {
        LOGGER.info("login sent.");
        ctx.writeAndFlush(buildLoginReqBody());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        DynamicMessage message = (DynamicMessage) msg;
        final DynamicMsgType msgType = message.getHeader().getMsgType();
        if(DynamicMsgType.LOGIN_RESP.equals(msgType)) {
            // 客户端接收到登录响应，启动心跳
            LOGGER.info("receive login resp. launch heartbeat.");
            ctx.fireUserEventTriggered(DynamicTriggerEvent.HEARTBEAT_EVENT);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private DynamicMessage buildLoginReqBody() {
        LoginReqBody loginReqBody = new LoginReqBody();
        loginReqBody.setUserName(USERNAME);
        loginReqBody.setPassword(PASSWD);

        return DynamicMessage.createMsgOfEncode(loginReqBody);
    }
}
