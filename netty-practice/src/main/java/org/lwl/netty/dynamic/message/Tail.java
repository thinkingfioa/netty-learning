package org.lwl.netty.dynamic.message;

/**
 * @author thinking_fioa
 * @createTime 2018/4/21
 * @description 消息体尾部
 */


public class Tail {

    private int checkSum;

    public int getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(int checkSum) {
        this.checkSum = checkSum;
    }

    /**
     * 属性字节数
     * @return
     */
    public static int byteSize() {
        return 4;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tail [");
        sb.append("checkSum=").append(checkSum).append("]");

        return sb.toString();
    }
}
