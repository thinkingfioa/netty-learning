package org.lwl.netty.dynamic.client;

import io.netty.channel.ChannelHandlerContext;
import org.lwl.netty.dynamic.client.handler.*;

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
            ctx.pipeline().addBefore(baseClazz.getName(), getClazz().getName(), sslHandler);
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
            ctx.pipeline().addBefore(baseClazz.getName(), getClazz().getName(), symEncryptionHandler);
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
            ctx.pipeline().addBefore(baseClazz.getName(), getClazz().getName(), randomCodeHandler);
            return randomCodeHandler;
        }
    },
    /**
     * 登录事件. {@see LoginHandler}
     */
    LOGIN_EVENT(LoginHandler.class) {
        @Override
        protected ITriggerHandler trigger0(ChannelHandlerContext ctx, Class<?> baseClazz) {
            LoginHandler loginHandler = new LoginHandler();
            ctx.pipeline().addBefore(baseClazz.getName(), getClazz().getName(), loginHandler);
            return loginHandler;
        }
    },
    /**
     * 心跳事件. {@see HeartbeatClientHandler}
     */
    HEARTBEAT_EVENT(HeartbeatClientHandler.class) {
        @Override
        protected ITriggerHandler trigger0(ChannelHandlerContext ctx, Class<?> baseClazz) {
            HeartbeatClientHandler htClientHandler = new HeartbeatClientHandler();
            ctx.pipeline().addBefore(baseClazz.getName(), getClazz().getName(), htClientHandler);
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

    public Class<? extends ITriggerHandler> getClazz() {
        return clazz;
    }

}
