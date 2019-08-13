package org.test;

import com.sun.istack.internal.logging.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author: Hao Qin
 * @Date: 19-8-13  下午5:25
 * @Version 1.0
 */
public class xxx {

    public static void main(String[] args) throws InterruptedException {


        Jedis jedis = new Jedis("127.0.0.1", 6379);
        Logger logger = Logger.getLogger(xxx.class);
            long start = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++) {
                jedis.set(String.valueOf(i), String.valueOf(i));
            }
            long end = System.currentTimeMillis();
            logger.info("the jedis total time is:" + (end - start));

        Pipeline pipe = jedis.pipelined(); // 先创建一个pipeline的链接对象
        long start_pipe = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            pipe.set(String.valueOf(i), String.valueOf(++i));
           // pipe.get(String.valueOf(i)).get();

        }
        pipe.sync(); // 获取所有的response
        long end_pipe = System.currentTimeMillis();
        logger.info("the pipe total time is:" + (end_pipe - start_pipe));

// 147 110 66
            BlockingQueue<String> logQueue = new LinkedBlockingQueue<String>();
            long begin = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++) {
                logQueue.put("i=" + i);
            }
            long stop = System.currentTimeMillis();
            logger.info("the BlockingQueue total time is:" + (stop - begin));
//        }

    }}

