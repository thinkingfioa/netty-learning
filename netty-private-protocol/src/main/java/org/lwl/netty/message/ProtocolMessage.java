package org.lwl.netty.message;

/**
 * @author thinking_fioa
 * @createTime 2018/4/21
 * @description 私有消息
 */


public class ProtocolMessage {

    private Header header;

    private Body body;

    private Tail tail;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Tail getTail() {
        return tail;
    }

    public void setTail(Tail tail) {
        this.tail = tail;
    }

    @Override
    public String toString() {
        return "ProtocolMessage: " + header.toString() + body.toString() + tail.toString();
    }
}
