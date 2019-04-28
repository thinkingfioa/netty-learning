package org.lwl.netty.dynamic.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.dynamic.DynamicMsgType;
import org.lwl.netty.dynamic.message.DynamicMessage;
import org.lwl.netty.dynamic.message.body.RandomCodeBody;

/**
 * @author thinking_fioa
 * @createTime 2018/8/13
 * @description
 */


public class RandomCodeServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LogManager.getLogger(RandomCodeServerHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        DynamicMessage message = (DynamicMessage) msg;
        final DynamicMsgType msgType = message.getHeader().getMsgType();
        if(DynamicMsgType.RANDOM_CODE.equals(msgType)) {
            LOGGER.info("receive randomCode req. accomplish ssl process");
            RandomCodeBody randomCodeBody = (RandomCodeBody)message.getBody();
            ctx.channel().writeAndFlush(buildSslBody(randomCodeBody.getRandomCode()));
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private DynamicMessage buildSslBody(String randomCode) {
        RandomCodeBody randomCodeBody = new RandomCodeBody();
        randomCodeBody.setRandomCode(randomCode);

        return DynamicMessage.createMsgOfEncode(randomCodeBody);
    }
}
