package org.lwl.netty.constant;

import java.util.EnumMap;
import java.lang.Short;
import java.util.HashMap;
import java.util.Map;

/**
 * @author thinking_fioa
 * @createTime 2018/5/27
 * @description 私有协议数据类型，供Client端订阅，目前有：新闻，体育，娱乐。
 */


public enum ProtocolDataType {
    /**
     * 新闻
     */
    NEWS((short)1, "news") ,
    /**
     * 体育
     */
    SPORTS((short)2, "sports"),
    /**
     * 娱乐
     */
    ENTERTAINMENT((short)3, "entertainment");

    private final short type;
    private final String typeDesc;

    private ProtocolDataType(short type, String typeDesc) {
        this.type = type;
        this.typeDesc = typeDesc;
    }

    /**
     * 根据type类型，获取{@code ProtocolDataType}
     * @param type 类型
     * @return 未找到类型，返回null
     */
    public static ProtocolDataType getDataType(final short type) {
        return ProtocolDataTypeHolder.getDataType(type);
    }

    public short getType() {
        return type;
    }

    @Override
    public String toString() {
        return typeDesc;
    }

    /**
     * 利用Map机制，加快查找
     */
    private static class ProtocolDataTypeHolder {

        private final static Map<Short, ProtocolDataType> dataTypeMap;

        static {
            dataTypeMap = new HashMap<>();
            for(ProtocolDataType dataType: ProtocolDataType.values()) {
                dataTypeMap.put(dataType.type, dataType);
            }
        }

        private static ProtocolDataType getDataType(final short typeKey) {
            return dataTypeMap.get(typeKey);
        }
    }
}
