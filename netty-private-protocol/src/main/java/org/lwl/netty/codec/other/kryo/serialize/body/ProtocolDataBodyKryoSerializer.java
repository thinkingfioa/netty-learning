package org.lwl.netty.codec.other.kryo.serialize.body;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.lwl.netty.codec.other.kryo.serialize.KryoDecoder;
import org.lwl.netty.codec.other.kryo.serialize.KryoEncoder;
import org.lwl.netty.message.body.ProtocolDataBody;

/**
 * @author thinking_fioa
 * @createTime 2018/6/10
 * @description
 */


public class ProtocolDataBodyKryoSerializer extends Serializer<ProtocolDataBody> {
    @Override
    public void write(Kryo kryo, Output output, ProtocolDataBody dataBody) {
        KryoEncoder.getInstance().writeLong(output, dataBody.getPkgSum());
        KryoEncoder.getInstance().writeLong(output, dataBody.getPkgSequenceNum());
        KryoEncoder.getInstance().writeInt(output, dataBody.getContentLen());
        KryoEncoder.getInstance().writeBytes(output, dataBody.getContent());
    }

    @Override
    public ProtocolDataBody read(Kryo kryo, Input input, Class<ProtocolDataBody> aClass) {
        long pkgSum = KryoDecoder.getInstance().readLong(input);
        long pkgSequenceNum = KryoDecoder.getInstance().readLong(input);
        int contentLen = KryoDecoder.getInstance().readInt(input);
        byte [] content = new byte[contentLen];
        KryoDecoder.getInstance().readBytes(input, content);

        ProtocolDataBody dataBody = new ProtocolDataBody();
        dataBody.setPkgSum(pkgSum);
        dataBody.setPkgSequenceNum(pkgSequenceNum);
        dataBody.setContentLen(contentLen);
        dataBody.setContent(content);

        return new ProtocolDataBody();
    }
}
