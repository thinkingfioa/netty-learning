package org.lwl.netty.dynamic.message.body;

import org.lwl.netty.dynamic.DynamicMsgType;
import org.lwl.netty.dynamic.message.Body;

/**
 * @author thinking_fioa
 * @createTime 2018/7/28
 * @description
 */


public class SymEncryption extends Body{
    /**
     * 对称加密方法
     */
    private String symEncryptionMethod;

    public String getSymEncryptionMethod() {
        return symEncryptionMethod;
    }

    public void setSymEncryptionMethod(String symEncryptionMethod) {
        this.symEncryptionMethod = symEncryptionMethod;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SymEncryption [");
        sb.append("symEncryptionMethod=").append(symEncryptionMethod).append("]");

        return sb.toString();
    }

    @Override
    public DynamicMsgType msgType() {
        return DynamicMsgType.SYM_ENCRYPTION;
    }
}
