package org.lwl.netty.codec.other.kryo.serialize.body;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.lwl.netty.codec.other.kryo.serialize.KryoDecoder;
import org.lwl.netty.codec.other.kryo.serialize.KryoEncoder;
import org.lwl.netty.constant.ProtocolDataType;
import org.lwl.netty.message.body.ProtocolSubBody;

import java.util.List;

/**
 * @author thinking_fioa
 * @createTime 2018/6/10
 * @description
 */


public class ProtocolSubBodyKryoSerializer extends Serializer<ProtocolSubBody> {
    @Override
    public void write(Kryo kryo, Output output, ProtocolSubBody subBody) {
        KryoEncoder.getInstance().writeList(kryo, output, subBody.getDataTypeList());
    }

    @Override
    public ProtocolSubBody read(Kryo kryo, Input input, Class<ProtocolSubBody> aClass) {
        List<ProtocolDataType> dataTypeList = KryoDecoder.getInstance().readList(kryo, input, ProtocolDataType.class);

        ProtocolSubBody subBody = new ProtocolSubBody();
        subBody.setDataTypeList(dataTypeList);

        return new ProtocolSubBody();
    }
}
