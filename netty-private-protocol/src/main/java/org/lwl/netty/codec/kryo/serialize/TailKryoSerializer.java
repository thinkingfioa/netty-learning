package org.lwl.netty.codec.kryo.serialize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.lwl.netty.codec.marshalling.MslDecoder;
import org.lwl.netty.message.Tail;

/**
 * @author thinking_fioa
 * @createTime 2018/5/17
 * @description
 */


public class TailKryoSerializer extends Serializer<Tail> {

    @Override
    public void write(Kryo kryo, Output output, Tail tail) {
        KryoEncoder.getInstance().writeInt(output, tail.getCheckSum());
    }

    @Override
    public Tail read(Kryo kryo, Input input, Class<Tail> aClass) {
        int checkSum = KryoDecoder.getInstance().readInt(input);

        Tail tail = new Tail();
        tail.setCheckSum(checkSum);

        return tail;
    }
}
