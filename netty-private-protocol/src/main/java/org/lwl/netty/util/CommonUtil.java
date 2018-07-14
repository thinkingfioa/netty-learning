package org.lwl.netty.util;

import io.netty.buffer.ByteBuf;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author thinking_fioa
 * @createTime 2018/5/24
 * @description 基础工具静态类
 */

public  final class CommonUtil {

    private CommonUtil() {
        throw new IllegalAccessError("can not use constructor about static class");
    }

    /**
     * 计算checkSum
     * @return
     */
    public static int calCheckSum(ByteBuf byteBuf, int length) {
        if(length <=0) {
            throw new IllegalArgumentException("length <= 0");
        }
        byte checkSum = 0;
        length = Math.min(length, byteBuf.writerIndex());
        for(int i = 0; i<length; i++) {
            checkSum += byteBuf.getByte(i);
        }

        return 0x00ff & checkSum;
    }

    public static int calCheckSum(byte[] bytes) {
        if(null == bytes) {
            throw new IllegalArgumentException("byte is null.");
        }
        byte checkSum = 0;
        for(int i =0; i<bytes.length; i++) {
            checkSum += bytes[i];
        }
        return 0x00ff & checkSum;
    }

    /**
     * jdk1.8的DateTimeFormat无法解析格式: yyyyMMddHHmmssSSS格式。会报错。
     */
    public static String nowTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateToStrFmt = DateTimeFormatter.ofPattern("yyyyMMdd hh:mm:ss");
        return dateToStrFmt.format(now);
    }
}