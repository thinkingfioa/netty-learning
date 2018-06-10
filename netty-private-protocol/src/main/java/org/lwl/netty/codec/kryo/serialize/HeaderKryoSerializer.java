package org.lwl.netty.codec.kryo.serialize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.codec.ProtocolDataDecoder;
import org.lwl.netty.config.ProtocolConfig;
import org.lwl.netty.message.Header;

import java.io.UnsupportedEncodingException;

/**
 * @author thinking_fioa
 * @createTime 2018/5/17
 * @description Kryo编码器。类: {@link Header}
 */


public class HeaderKryoSerializer extends Serializer<Header> {
    private static final Logger LOGGER = LogManager.getLogger(HeaderKryoSerializer.class);

    @Override
    public void write(Kryo kryo, Output output, Header header) {
        output.writeInt(header.getMsgLen());
        output.writeLong(header.getMsgNum());
        final String msgType = header.getMsgType().getMsgType();
        output.writeInt(msgType.length());
        try {
            output.write(header.getMsgType().getMsgType().getBytes(ProtocolConfig.getCharsetFormat()));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("getBytes error. msgType: {}", header.getMsgType().getMsgType())
        }
    }

    @Override
    public Header read(Kryo kryo, Input input, Class<Header> aClass) {
        return null;
    }
}
