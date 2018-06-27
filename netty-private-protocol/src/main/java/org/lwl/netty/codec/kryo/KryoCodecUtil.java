package org.lwl.netty.codec.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwl.netty.codec.IMessageCodecUtil;
import org.lwl.netty.codec.marshalling.MarshallingCodecUtil;
import org.lwl.netty.config.ProtocolConfig;
import org.lwl.netty.message.ProtocolMessage;
import org.lwl.netty.message.Tail;
import org.lwl.netty.util.CommonUtil;

/**
 * @author thinking_fioa
 * @createTime 2018/4/21
 * @description
 */


public class KryoCodecUtil implements IMessageCodecUtil<Object>{
    private static final Logger LOGGER = LogManager.getLogger(MarshallingCodecUtil.class);
    private static final int PKG_MAX_LEN = ProtocolConfig.getPkgMaxLen();
    private static final byte [] LENGTH_PLACEHOLDER = new byte[4];

    @Override
    public void encode(ChannelHandlerContext ctx, ByteBuf outByteBuf, Object object) throws Exception {
        Kryo kryo = KryoHolder.get();
        ByteBufOutputStream byteBufOutputStream = null;
        try {
            int startIdx = outByteBuf.writerIndex();
            byteBufOutputStream = new ByteBufOutputStream(outByteBuf);
            byteBufOutputStream.write(LENGTH_PLACEHOLDER);
            Output output = new Output(PKG_MAX_LEN, -1);
            output.setOutputStream(byteBufOutputStream);
            kryo.writeClassAndObject(output, object);

            output.flush();
            output.close();
            // 更新最大长度字段
            outByteBuf.setInt(startIdx, outByteBuf.writerIndex());
            // 计算并更新checkSum
            int checkSum = CommonUtil.calCheckSum(outByteBuf, outByteBuf.writerIndex() - Tail.byteSize());
            outByteBuf.setInt(outByteBuf.writerIndex() - Tail.byteSize(), checkSum);

        } finally {
            if(null != byteBufOutputStream) {
                byteBufOutputStream.close();
            }
        }
    }

    @Override
    public Object decode(ChannelHandlerContext ctx, ByteBuf inByteBuf) {
        int msgLen = inByteBuf.readInt();

        Input input = new Input(new ByteBufInputStream(inByteBuf));
        Kryo kryo = KryoHolder.get();
        Object message = kryo.readClassAndObject(input);
        if(!(message instanceof ProtocolMessage)) {
            LOGGER.error("decode fail.msgLen: {}, {}", msgLen, message);
            return null;
        }
        ProtocolMessage protocolMessage = (ProtocolMessage)message;
        int calCheckSum = CommonUtil.calCheckSum(inByteBuf, inByteBuf.writerIndex() - Tail.byteSize());
        if(calCheckSum != protocolMessage.getTail().getCheckSum()) {
            LOGGER.error("checkSum error. calCheckSum:{}, sendCheckSum:{}, message:{}", calCheckSum, protocolMessage.getTail().getCheckSum(), message);
            return null;
        }
        LOGGER.debug(" <-- read msgLen:{}, {}", msgLen, message);
        return message;
    }
}
