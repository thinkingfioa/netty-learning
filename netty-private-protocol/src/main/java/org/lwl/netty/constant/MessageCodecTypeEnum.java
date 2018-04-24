package org.lwl.netty.constant;

/**
 * @author thinking_fioa
 * @createTime 2018/4/24
 * @description
 */


public enum MessageCodecTypeEnum {
    // Kryo 编码
    KRYO("kryo"),
    // Marshalling 编码
    MARSHALLING("marshalling"),
    // Protobuf 编码
    PROTOBUF("protobuf"),
    // Thrift 编码
    THRIFT("thrift"),
    // avro 编码
    AVRO("avro"),
    // 二进制编码
    BINARY_CODEC("binary");


    MessageCodecTypeEnum(final String codecType) {
        this.codecType = codecType;
    }

    private  final String codecType;

    public String getCodecType() {
        return codecType;
    }
}
