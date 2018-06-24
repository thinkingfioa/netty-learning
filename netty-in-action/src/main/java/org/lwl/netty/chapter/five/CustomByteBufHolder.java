package org.lwl.netty.chapter.five;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.DefaultByteBufHolder;

/**
 * 友情提示：建议覆盖DefaultByteBufHolder的所有方法，否则可能出现{@link ClassCastException}<br>
 * 希望有更好的方式来实现自定义ByteBuf方式
 *
 * @author thinking_fioa
 * @createTime 2018/6/24
 * @description
 */


public class CustomByteBufHolder extends DefaultByteBufHolder{

    private String protocolName;

    public CustomByteBufHolder(String protocolName, ByteBuf data) {
        super(data);
        this.protocolName = protocolName;
    }

    @Override
    public CustomByteBufHolder replace(ByteBuf data) {
        return new CustomByteBufHolder(protocolName, data);
    }

    @Override
    public CustomByteBufHolder retain() {
        super.retain();
        return this;
    }

    @Override
    public CustomByteBufHolder touch() {
        super.touch();
        return this;
    }

    @Override
    public CustomByteBufHolder touch(Object hint) {
        super.touch(hint);
        return this;
    }

    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
    }
}
