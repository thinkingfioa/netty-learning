package org.lwl.netty;

import java.nio.charset.Charset;

/**
 * @author thinking_fioa
 * @createTime 2018/7/28
 * @description
 */


public final class Config {
    private Config() {
        throw new IllegalAccessError("static class, can not constructor.");
    }

    public static String getCharsetFormatStr() {
        return "UTF-8";
    }

    public static Charset getCharsetFormat() {
        return Charset.forName(getCharsetFormatStr());
    }
}
