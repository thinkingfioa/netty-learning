package org.lwl.netty.server.handler.protobuf;

import org.lwl.netty.message.protobuf.Header;
import org.lwl.netty.message.protobuf.ProtocolMessage;
import org.lwl.netty.message.protobuf.Tail;
import org.lwl.netty.util.CommonUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author thinking_fioa
 * @createTime 2018/7/11
 * @description 帮助Protobuf编码和解码工具类
 */

final class ProtobufServerCodecHelper {

    private static final AtomicLong MSG_NUM = new AtomicLong(0);

    private ProtobufServerCodecHelper() {
        throw new IllegalAccessError("can not use constructor.");
    }

    public static Header.HeaderP.Builder generateHeaderBuilder(Header.MessageTypeEnum msgType) {
        Header.HeaderP.Builder headerBuilder = Header.HeaderP.newBuilder();

        headerBuilder.setMsgNum(MSG_NUM.get());
        headerBuilder.setMsgType(msgType);
        headerBuilder.setMsgTime(CommonUtil.nowTime());
        // 下面的值，随机填。主要目的是证明协议支持多种类型格式
        headerBuilder.setFlag(2);
        headerBuilder.setOneByte(3);
        Map<String, String> attachment = new HashMap<>();
        attachment.put("name", "luweilin");
        attachment.put("age", "20");
        headerBuilder.putAllAttachment(attachment);

        return headerBuilder;
    }

    public static Tail.TailP.Builder generateTailBuilder(ProtocolMessage.ProtocolMessageP.Builder msgBuilder) {
        byte[] bytes = msgBuilder.build().toByteArray();
        int checkSum = CommonUtil.calCheckSum(bytes);
        Tail.TailP.Builder tailBuilder = Tail.TailP.newBuilder();
        tailBuilder.setCheckSum(checkSum);

        return tailBuilder;
    }

}
