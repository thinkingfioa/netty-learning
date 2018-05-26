package org.lwl.netty.util;

import java.time.LocalDate;
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
    public static int calCheckSum() {
        //TODO:: 提供checkSum计算
        return 100;
    }

    /**
     *
     */

    private static String calTime() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter dateToStrFmt = DateTimeFormatter.ofPattern("yyyyMMdd HH:MM:SS");
        return dateToStrFmt.format(now);
    }

    public static void main(String [] args) {
        System.out.println(calTime());
    }
}