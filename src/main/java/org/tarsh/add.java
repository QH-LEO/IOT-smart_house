package org.tarsh;

import redis.clients.jedis.Jedis;

/**
 * @Author: Hao Qin
 * @Date: 19-8-10  下午8:35
 * @Version 1.0
 */
public class add {
    public static void main(String[] args) {
        Jedis j=new Jedis("127.0.0.1",6379);
        for(int i=0;i<1000000;i++){
            j.del(String.valueOf(i));
        }
    }
}
