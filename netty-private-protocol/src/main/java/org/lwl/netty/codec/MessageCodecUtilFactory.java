package org.lwl.netty.codec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.codec.kryo.KryoCodecUtil;
import org.lwl.netty.codec.marshalling.MarshallingCodecUtil;
import org.lwl.netty.codec.protobuf.ProtobufCodecUtil;
import org.lwl.netty.config.ProtocolConfig;

/**
 * @author thinking_fioa
 * @createTime 2018/5/12
 * @description
 */


public final class MessageCodecUtilFactory {
    private static final Logger LOGGER = LogManager.getLogger(MessageCodecUtilFactory.class);

    private static final byte codecType = ProtocolConfig.getCodecType();

    private MessageCodecUtilFactory() {
        throw new IllegalAccessError("can not use constructor.");
    }

    public static IMessageCodecUtil getCodecUtil() {
        for(MessageCodec codecEnum : MessageCodec.values()) {
            if(codecEnum.codec == codecType) {
                LOGGER.info("protocolMsg codecUtil: {}", codecEnum);
                return codecEnum.createCodecUtil();
            }
        }

        LOGGER.info("protocolMsg codecUtil: {}", MessageCodec.MARSHALLING_CODEC);
        return MessageCodec.MARSHALLING_CODEC.createCodecUtil();
    }

    private enum MessageCodec {
        // Marshalling 编码
        MARSHALLING_CODEC((byte)1, "MarshallingCodec"){
            @Override
            public IMessageCodecUtil createCodecUtil(){
                return new MarshallingCodecUtil();
            }
        },
        // kryo 编码
        KRYO_CODEC((byte)2, "KryoCodec") {
            @Override
            public IMessageCodecUtil createCodecUtil(){
                return new KryoCodecUtil();
            }
        },
        // Protobuf 编码
        PROTOBUF_CODEC((byte)3, "ProtobufCodec") {
            @Override
            public IMessageCodecUtil createCodecUtil(){
                return new ProtobufCodecUtil();
            }
        },
        // thrift 编码
        THRIFT_CODEC((byte)4, "ThriftCodec") {
            @Override
            public IMessageCodecUtil createCodecUtil(){
                //TODO::
                return null;
            }
        },
        // avro 编码
        AVRO_CODEC((byte)5, "AvroCodec") {
            @Override
            public IMessageCodecUtil createCodecUtil(){
                //TODO::
                return null;
            }
        };

        private final byte codec;
        private final String desc;

        MessageCodec(final byte codec, final String desc) {
            this.codec = codec;
            this.desc = desc;
        }

        public abstract IMessageCodecUtil createCodecUtil();

        @Override
        public String toString() {
            return desc;
        }
    }

}
