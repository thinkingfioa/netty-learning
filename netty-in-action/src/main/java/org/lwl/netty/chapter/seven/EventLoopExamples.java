package org.lwl.netty.chapter.seven;

import java.util.ArrayList;
import java.util.List;

/**
 * @author thinking_fioa
 * @createTime 2018/8/14
 * @description
 */


public class EventLoopExamples {
    /**
     * 代码清单 7-1
     */
    public static void executeTaskInEventLoop() {
        boolean terminated = true;

        while (!terminated) {
            List<Runnable> readyEvents = blockUntilEventsReady();
            for (Runnable ev: readyEvents) {
                ev.run();
            }
        }
    }

    public static List<Runnable> blockUntilEventsReady() {
        return new ArrayList<>();
    }
}
