package org.lwl.netty.codec.other.kryo.serialize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.message.Body;
import org.lwl.netty.message.Header;
import org.lwl.netty.message.ProtocolMessage;
import org.lwl.netty.message.Tail;

/**
 * @author thinking_fioa
 * @createTime 2018/6/10
 * @description {@link ProtocolMessageKryoSerializer} Kryo编解码
 */


public class ProtocolMessageKryoSerializer extends Serializer<ProtocolMessage> {
    private static final Logger LOGGER = LogManager.getLogger(ProtocolMessageKryoSerializer.class);

    @Override
    public void write(Kryo kryo, Output output, ProtocolMessage protocolMessage) {
        KryoEncoder.getInstance().writeObject(kryo, output, protocolMessage.getHeader());
        KryoEncoder.getInstance().writeObject(kryo, output, protocolMessage.getBody());
        KryoEncoder.getInstance().writeObject(kryo, output, protocolMessage.getTail());
    }

    @Override
    public ProtocolMessage read(Kryo kryo, Input input, Class<ProtocolMessage> aClass) {
        Header header = (Header)KryoDecoder.getInstance().readObject(kryo, input);
        Body body = (Body)KryoDecoder.getInstance().readObject(kryo, input);
        Tail tail = (Tail)KryoDecoder.getInstance().readObject(kryo, input);

        return ProtocolMessage.createMsgOfDecode(header, body, tail);
    }
}
