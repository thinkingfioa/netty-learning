package org.lwl.netty.dynamic.message;

/**
 * @author thinking_fioa
 * @createTime 2018/4/21
 * @description 自定义消息格式消息
 */


public class DynamicMessage {

    private Header header;

    private Body body;

    private Tail tail;

    private DynamicMessage(Header header, Body body, Tail tail) {
        this.header = header;
        this.body = body;
        this.tail = tail;
    }

    private DynamicMessage(Body body) {
        this.header = new Header();
        this.body = body;
        this.tail = new Tail();
    }

    public static DynamicMessage createMsgOfEncode(Body body) {
        return new DynamicMessage(body);
    }

    public static DynamicMessage createMsgOfDecode(Header header, Body body, Tail tail) {
        return new DynamicMessage(header, body, tail);
    }

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
        return "DynamicMessage: " + header.toString() + body.toString() + tail.toString();
    }
}
