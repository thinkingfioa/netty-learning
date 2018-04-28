package org.lwl.netty.message;

/**
 * @author thinking_fioa
 * @createTime 2018/4/21
 * @description 消息体尾部
 */


public class Tail {
    private int copyRightId;
    private String copyRight;

    public int getCopyRightId() {
        return copyRightId;
    }

    public void setCopyRightId(int copyRightId) {
        this.copyRightId = copyRightId;
    }

    public String getCopyRight() {
        return copyRight;
    }

    public void setCopyRight(String copyRight) {
        this.copyRight = copyRight;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tail [ ")
                .append("copyRightId=").append(copyRightId)
                .append(", copyRight=").append(copyRight).append(" ]");

        return sb.toString();
    }
}
