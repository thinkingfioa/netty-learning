package org.lwl.netty.codec.marshalling;

import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;
import org.lwl.netty.codec.marshalling.serialize.IBodySerializer;
import org.lwl.netty.codec.marshalling.serialize.body.*;
import org.lwl.netty.constant.MessageTypeEnum;
import org.lwl.netty.constant.ProtocolConstant;
import org.lwl.netty.message.Body;

import java.util.EnumMap;

/**
 * @author thinking_fioa
 * @createTime 2018/5/13
 * @description
 */


public final class MarshallingAdapterFactory {

    private MarshallingAdapterFactory() {
        throw new IllegalAccessError("can not use constructor.");
    }

    private static EnumMap<MessageTypeEnum, IBodySerializer<? extends Body>> bodySerializerMap = new EnumMap<>(MessageTypeEnum.class);

    static {
        bodySerializerMap.put(MessageTypeEnum.UNKNOWN, DefaultBodySerializer.getInstance());
        bodySerializerMap.put(MessageTypeEnum.HEARTBEAT_REQ, HeartbeatReqBodySerializer.getInstance());
        bodySerializerMap.put(MessageTypeEnum.HEARTBEAT_RESP, HeartbeatRespBodySerializer.getInstance());
        bodySerializerMap.put(MessageTypeEnum.LOGIN_REQ, LoginReqBodySerializer.getInstance());
        bodySerializerMap.put(MessageTypeEnum.LOGIN_RESP, LoginRespBodySerializer.getInstance());
        bodySerializerMap.put(MessageTypeEnum.LOGOUT, LogoutBodySerializer.getInstance());
        bodySerializerMap.put(MessageTypeEnum.PROTOCOL_SUB, ProtocolSubBodySerializer.getInstance());
        bodySerializerMap.put(MessageTypeEnum.PROTOCOL_DATA, ProtocolDataBodySerializer.getInstance());
    }

    public static IBodySerializer<? extends Body> getBodySerializer(final MessageTypeEnum msgType) {
        IBodySerializer<? extends Body> bodySerializer = bodySerializerMap.get(msgType);

        return null != bodySerializer ? bodySerializer: DefaultBodySerializer.getInstance();
    }

    public static MarshallingDecoderAdapter buildDecoderAdapter() {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory, configuration);

        return new MarshallingDecoderAdapter(provider, ProtocolConstant.getMessageMaxSize());
    }

    public static MarshallingEncoderAdapter buildEncoderAdapter() {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        MarshallerProvider provider = new DefaultMarshallerProvider(marshallerFactory, configuration);

        return new MarshallingEncoderAdapter(provider);
    }
}
