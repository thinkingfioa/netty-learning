package org.lwl.netty.dynamic.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateHandler;
import org.lwl.netty.dynamic.DynamicConfig;
import org.lwl.netty.dynamic.client.handler.*;

import java.util.concurrent.TimeUnit;

/**
 * @author thinking_fioa
 * @createTime 2018/8/6
 * @description 自定义的Trigger事件
 */


public enum DynamicTriggerEvent {
    /**
     * 触发SSL事件. {@see SslHandler}
     */
    SSL_EVENT(SslHandler.class) {
        @Override
        protected ITriggerHandler trigger0(ChannelHandlerContext ctx, Class<?> baseClazz) {
            SslHandler sslHandler = new SslHandler();
            ctx.pipeline().addBefore(baseClazz.getSimpleName(), getClazz().getSimpleName(), sslHandler);
            return sslHandler;
        }
    },
    /**
     * 对称加密事件. {@see SymEncryptionHandler}
     */
    SYM_ENCRYPTION_EVENT(SymEncryptionHandler.class) {
        @Override
        protected ITriggerHandler trigger0(ChannelHandlerContext ctx, Class<?> baseClazz) {
            SymEncryptionHandler symEncryptionHandler = new SymEncryptionHandler();
            ctx.pipeline().addBefore(baseClazz.getSimpleName(), getClazz().getSimpleName(), symEncryptionHandler);
            return symEncryptionHandler;
        }
    },
    /**
     * 随机数加密。 {@see SymEncryptionHandler}
     */
    RANDOM_CODE_EVENT(RandomCodeHandler.class) {
        @Override
        protected ITriggerHandler trigger0(ChannelHandlerContext ctx, Class<?> baseClazz) {
            RandomCodeHandler randomCodeHandler = new RandomCodeHandler();
            ctx.pipeline().addBefore(baseClazz.getSimpleName(), getClazz().getSimpleName(), randomCodeHandler);
            return randomCodeHandler;
        }
    },
    /**
     * 心跳事件. {@see HeartbeatClientHandler}
     */
    HEARTBEAT_EVENT(HeartbeatClientHandler.class) {
        @Override
        protected ITriggerHandler trigger0(ChannelHandlerContext ctx, Class<?> baseClazz) {
            ctx.channel().pipeline().addBefore(baseClazz.getSimpleName() ,IdleStateHandler.class.getSimpleName(), new IdleStateHandler(DynamicConfig.getHtInterval(), DynamicConfig.getHtInterval(), 0, TimeUnit.SECONDS));

            HeartbeatClientHandler htClientHandler = new HeartbeatClientHandler();
            ctx.pipeline().addBefore(baseClazz.getSimpleName(), getClazz().getSimpleName(), htClientHandler);
            return htClientHandler;
        }
    };

    private Class<? extends ITriggerHandler> clazz;

    DynamicTriggerEvent(Class<? extends ITriggerHandler> clazz) {
        this.clazz = clazz;
    }

    public void trigger(ChannelHandlerContext ctx, Class<?> baseClazz) {
        trigger0(ctx, baseClazz).launch(ctx);
    }

    protected  abstract ITriggerHandler trigger0(ChannelHandlerContext ctx, Class<?> baseClazz);

    public Class<?> getClazz() {
        return clazz;
    }

    @Override
    public String toString() {
        return clazz.getSimpleName();
    }

}
