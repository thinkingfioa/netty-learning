package org.lwl.netty.client.handler.protobuf;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.config.ProtocolConfig;
import org.lwl.netty.message.protobuf.Tail;
import org.lwl.netty.message.protobuf.Header;
import org.lwl.netty.message.protobuf.LoginReqBody;
import org.lwl.netty.message.protobuf.ProtocolMessage;

/**
 * @author thinking_fioa
 * @createTime 2018/7/11
 * @description
 */
@ChannelHandler.Sharable
public class LoginReqHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LogManager.getLogger(LoginReqHandler.class);

    private static final String USERNAME = ProtocolConfig.getUserName();
    private static final String PASSWORD = ProtocolConfig.getPassword();

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ProtocolMessage.ProtocolMessageP msg = buildLoginReqMsg();
        LOGGER.info("channel active. login reg sent.");
        ctx.writeAndFlush(msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object message) {
        ProtocolMessage.ProtocolMessageP msg = (ProtocolMessage.ProtocolMessageP) message;
        final Header.MessageTypeEnum msgType = msg.getHeader().getMsgType();
        if(Header.MessageTypeEnum.LOGIN_RESP.equals(msgType)) {
            LOGGER.info("receive login resp");
        } else {
            ctx.fireChannelRead(message);
        }

    }

    private ProtocolMessage.ProtocolMessageP buildLoginReqMsg() {
        ProtocolMessage.ProtocolMessageP.Builder msgBuilder = ProtocolMessage.ProtocolMessageP.newBuilder();
        // Header
        Header.HeaderP.Builder headerBuilder = ProtobufCodecHelper.generateHeaderBuilder(Header.MessageTypeEnum.LOGIN_REQ);
        msgBuilder.setHeader(headerBuilder);
        // Body
        LoginReqBody.LoginReqBodyP.Builder loginReqBuilder = LoginReqBody.LoginReqBodyP.newBuilder();
        loginReqBuilder.setUserName(USERNAME);
        loginReqBuilder.setPassword(PASSWORD);
        msgBuilder.setLoginReqBody(loginReqBuilder.build());
        // Tail
        Tail.TailP.Builder tailBuilder = ProtobufCodecHelper.generateTailBuilder(msgBuilder);
        msgBuilder.setTail(tailBuilder);
        return msgBuilder.build();
    }
}
