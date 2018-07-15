package org.lwl.netty.server.handler.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.config.ProtocolConfig;
import org.lwl.netty.message.protobuf.*;

/**
 * @author thinking_fioa
 * @createTime 2018/7/15
 * @description
 */


public class LoginRespHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LogManager.getLogger(LoginRespHandler.class);

    private static final String USERNAME = ProtocolConfig.getUserName();
    private static final String PASSWORD = ProtocolConfig.getPassword();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ProtocolMessage.ProtocolMessageP message = (ProtocolMessage.ProtocolMessageP) msg;
        Header.MessageTypeEnum msgType = message.getHeader().getMsgType();
        if(Header.MessageTypeEnum.LOGIN_REQ.equals(msgType)) {
            LOGGER.info("receive login req.");
            LoginReqBody.LoginReqBodyP loginBody = message.getLoginReqBody();
            if(checkPermission(loginBody)) {
                LOGGER.info("user check pass. login resp sent.");
                ctx.writeAndFlush(buildLoginResp());
            }
        }
    }

    private boolean checkPermission(LoginReqBody.LoginReqBodyP reqBody) {
        final String userName  = reqBody.getUserName();
        final String passwd = reqBody.getPassword();

        return USERNAME.equals(userName) && PASSWORD.equals(passwd);
    }

    private ProtocolMessage.ProtocolMessageP buildLoginResp() {
        ProtocolMessage.ProtocolMessageP.Builder msgBuilder = ProtocolMessage.ProtocolMessageP.newBuilder();
        // Header
        Header.HeaderP.Builder headerBuilder = ProtobufServerCodecHelper.generateHeaderBuilder(Header.MessageTypeEnum.LOGIN_RESP);
        msgBuilder.setHeader(headerBuilder);
        // Body
        LoginRespBody.LoginRespBodyP.Builder loginRespBuilder = LoginRespBody.LoginRespBodyP.newBuilder();
        msgBuilder.setLoginRespBody(loginRespBuilder);
        // Tail
        Tail.TailP.Builder tailBuilder = ProtobufServerCodecHelper.generateTailBuilder(msgBuilder);
        msgBuilder.setTail(tailBuilder);

        return msgBuilder.build();
    }

}
