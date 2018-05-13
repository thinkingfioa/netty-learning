package org.lwl.netty.codec.marshalling;

import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;
import org.lwl.netty.constant.ProtocolConfig;

/**
 * @author thinking_fioa
 * @createTime 2018/5/13
 * @description
 */


public final class MarshallingAdapterFactory {

    private MarshallingAdapterFactory() {
        throw new IllegalAccessError("can not use constructor.");
    }

    public static MarshallingDecoderAdapter buildDecoderAdapter() {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory, configuration);

        return new MarshallingDecoderAdapter(provider, ProtocolConfig.getMessageMaxSize());
    }

    public static MarshallingEncoderAdapter buildEncoderAdapter() {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        MarshallerProvider provider = new DefaultMarshallerProvider(marshallerFactory, configuration);

        return new MarshallingEncoderAdapter(provider);
    }
}
