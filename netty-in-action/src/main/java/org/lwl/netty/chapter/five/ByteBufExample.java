package org.lwl.netty.chapter.five;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
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

    /**
     * 代码清单 5-2 直接内存
     */
    public static void directBuffer() { // 不是数组支撑
        ByteBuf directBuf = Unpooled.directBuffer();
        if (!directBuf.hasArray()) {
            int length = directBuf.readableBytes();
            byte[] array = new byte[length];
            directBuf.getBytes(directBuf.readerIndex(), array);
            handleArray(array, 0, length);
        }
    }

    /**
     * 代码清单 5-4  复合内存
     */
    public static void byteBufComposite() {
        CompositeByteBuf messageBuf = Unpooled.compositeBuffer();
        ByteBuf headerBuf = Unpooled.buffer(); // can be backing or direct
        ByteBuf bodyBuf = Unpooled.directBuffer();   // can be backing or direct
        messageBuf.addComponents(headerBuf, bodyBuf);
        messageBuf.removeComponent(0); // remove the header
        for (ByteBuf buf : messageBuf) {
            System.out.println(buf.toString());
        }
    }

    /**
     * 代码清单 5-6 随机访问索引
     */
    public static void byteBufRelativeAccess() {
        ByteBuf buffer = Unpooled.buffer(); //get reference form somewhere
        for (int i = 0; i < buffer.capacity(); i++) {
            byte b = buffer.getByte(i);
            System.out.println((char) b);
        }
    }


    private static void handleArray(byte[] array, int offset, int len) {}
}
