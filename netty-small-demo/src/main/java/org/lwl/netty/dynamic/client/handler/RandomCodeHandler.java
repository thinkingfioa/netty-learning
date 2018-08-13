package org.lwl.netty.dynamic.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.dynamic.client.DynamicTriggerEvent;
import org.lwl.netty.dynamic.message.DynamicMessage;
import org.lwl.netty.dynamic.message.body.RandomCodeBody;

/**
 * @author thinking_fioa
 * @createTime 2018/7/28
 * @description 产生随机码，使用服务端密钥加密后，发送至服务器
 */


public class RandomCodeHandler extends ChannelInboundHandlerAdapter implements ITriggerHandler{
    private static final Logger LOGGER = LogManager.getLogger(RandomCodeHandler.class);

    private static final String RANDOM_CODE = "luweilin";

    @Override
    public void launch(ChannelHandlerContext ctx) {
        LOGGER.info("randomCode sent.");
        ctx.writeAndFlush(buildRandomCodeBody());
        ctx.fireUserEventTriggered(DynamicTriggerEvent.LOGIN_EVENT);
    }

    private DynamicMessage buildRandomCodeBody() {
        RandomCodeBody randomCodeBody = new RandomCodeBody();
        randomCodeBody.setRandomCode(RANDOM_CODE);

        return DynamicMessage.createMsgOfEncode(randomCodeBody);
    }
}
