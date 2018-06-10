package org.lwl.netty.chapter.five;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author thinking_fioa
 * @createTime 2018/6/10
 * @description 代码清单: 5-1、5-2、5-3、5-4、5-5、5-6、5-7、5-8、5-9、5-10、5-11、5-12、5-13、5-14、5-15、5-16
 */


public class ByteBufExample {
    /**
     * 代码清单 5-1 堆缓冲区
     */
    public static void heapBuffer() {
        // 创建Java堆缓冲区
        ByteBuf heapBuf = Unpooled.buffer();
        if (heapBuf.hasArray()) {
            byte[] array = heapBuf.array();
            int offset = heapBuf.arrayOffset() + heapBuf.readerIndex();
            int length = heapBuf.readableBytes();
            handleArray(array, offset, length);
        }
    }

    private static void handleArray(byte[] array, int offset, int len) {}
}
