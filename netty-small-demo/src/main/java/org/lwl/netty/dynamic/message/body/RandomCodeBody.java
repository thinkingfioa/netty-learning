package org.lwl.netty.dynamic.message.body;

import org.lwl.netty.dynamic.DynamicMsgType;
import org.lwl.netty.dynamic.message.Body;

/**
 * @author thinking_fioa
 * @createTime 2018/7/28
 * @description
 */


public class RandomCodeBody extends Body{

    private String randomCode;

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RandomCodeBody [");
        sb.append("randomCode=").append(randomCode).append("]");

        return sb.toString();
    }

    @Override
    public DynamicMsgType msgType() {
        return DynamicMsgType.RANDOM_CODE;
    }
}
