package org.lwl.netty.codec.marshalling;

/**
 * @author thinking_fioa
 * @createTime 2018/5/12
 * @description
 */


class Decoder {

    private static final Decoder INSTANCE = new Decoder();

    public static Decoder getInstance() {
        return INSTANCE;
    }

    private String name;

    private Decoder() {
        this.name = "";
    }

    public String getName() {
        return name;
    }
}
