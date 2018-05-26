package org.lwl.netty.message;

/**
 * @author thinking_fioa
 * @createTime 2018/4/21
 * @description 自定义消息格式消息
 */


public class ProtocolMessage {

    private Header header;

    private IBody body;

    private Tail tail;

    private ProtocolMessage(Header header, IBody body, Tail tail) {
        this.header = header;
        this.body = body;
        this.tail = tail;
    }

    private ProtocolMessage(IBody body) {
        this.header = new Header();
        this.body = body;
        this.tail = new Tail();
    }

    public static ProtocolMessage createMsgOfEncode(IBody body) {
        return new ProtocolMessage(body);
    }

    public static ProtocolMessage createMsgOfDecode(Header header, IBody body, Tail tail) {
        return new ProtocolMessage(header, body, tail);
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public IBody getBody() {
        return body;
    }

    public void setBody(IBody body) {
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
