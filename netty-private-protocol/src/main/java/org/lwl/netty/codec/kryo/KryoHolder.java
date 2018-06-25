package org.lwl.netty.codec.kryo;

import com.esotericsoftware.kryo.Kryo;

/**
 * @author thinking_fioa
 * @createTime 2018/5/17
 * @description Kryo编码器是一个非线程安全，所以使用ThreadLocal
 */


public class KryoHolder {
    private static ThreadLocal<Kryo> threadLocalKryo = new ThreadLocal<Kryo>(){
        @Override
        protected Kryo initialValue() {
            Kryo kryo = new KryoReflectionFactory();

            return kryo;
        }
    };

    public static Kryo get() {
        return threadLocalKryo.get();
    }
}
