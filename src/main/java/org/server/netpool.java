package org.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Hao Qin
 * @Date: 19-7-26  上午1:23
 * @Version 1.0
 */
public class netpool {

//newcachedpool 按需生成线程归还重用
//jdk原文描述，学好英语 ！
// Creates a thread pool that creates new threads as needed, but
//will reuse previously constructed threads when they are
//available.

    public static ExecutorService pool = Executors.newCachedThreadPool();
    public static void doWork(Runnable runnable) {
        pool.execute(runnable);
    }
}

