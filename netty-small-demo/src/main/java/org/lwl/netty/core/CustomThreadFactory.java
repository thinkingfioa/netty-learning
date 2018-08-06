package org.lwl.netty.core;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author thinking_fioa
 * @createTime 2018/4/24
 * @description 线程工厂方式，创建定制化线程
 */

public class CustomThreadFactory implements ThreadFactory{
    private final AtomicInteger threadNum = new AtomicInteger(0);
    private final String prefix;
    private final boolean daemon;
    private final ThreadGroup group;

    public CustomThreadFactory(final String prefix) {
        this(prefix, true);
    }

    public CustomThreadFactory(final String prefix, final boolean daemon){
        this.prefix = prefix;
        this.daemon = daemon;
        // 防止运行未知Java程序存在恶意代码。启用Java安全管理器
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable r) {
        String threadName = prefix + "_" + threadNum.getAndIncrement();
        Thread t = new Thread(group, r, threadName);
        t.setDaemon(daemon);

        return t;
    }
}
