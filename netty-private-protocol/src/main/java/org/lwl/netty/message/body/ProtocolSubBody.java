package org.lwl.netty.message.body;

import org.lwl.netty.constant.MessageTypeEnum;
import org.lwl.netty.constant.ProtocolDataType;
import org.lwl.netty.message.Body;

import java.util.List;

/**
 * @author thinking_fioa
 * @createTime 2018/5/26
 * @description 自定义协议数据消息。服务端根据客户端订阅的类型，发送具体对应的数据
 */


public class ProtocolSubBody extends Body{

    /**
     * 订阅的频道类型
     */
    private List<ProtocolDataType> dataTypeList;

    public List<ProtocolDataType> getDataTypeList() {
        return dataTypeList;
    }

    public void setDataTypeList(List<ProtocolDataType> dataTypeList) {
        this.dataTypeList = dataTypeList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProtocolSubBody [");
        sb.append("dataTypeList=").append(dataTypeList).append("]");

        return sb.toString();
    }

    @Override
    public MessageTypeEnum msgType() {
        return MessageTypeEnum.PROTOCOL_SUB;
    }

}
