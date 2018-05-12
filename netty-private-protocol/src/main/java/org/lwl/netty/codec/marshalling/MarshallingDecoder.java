package org.lwl.netty.codec.marshalling;

/**
 * @author thinking_fioa
 * @createTime 2018/5/12
 * @description
 */


public class MarshallingDecoder {

    private static final MarshallingDecoder INSTANCE = new MarshallingDecoder();

    public static MarshallingDecoder getInstance() {
        return INSTANCE;
    }

    private String name;

    private MarshallingDecoder() {
        this.name = "";
    }

    public String getName() {
        return name;
    }
}
