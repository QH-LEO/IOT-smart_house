package org.verify;

import org.apache.log4j.Logger;
import org.utils.userinfo;
import redis.clients.jedis.Jedis;

/**
 * @Author: Hao Qin
 * @Date: 19-7-30  上午10:27
 * @Version 1.0
 */
public class acceptsms {
    static Logger LOGGER=Logger.getLogger(acceptsms.class);
    public void send1(userinfo u){
        SendSms s=new SendSms();
        int i=s.send(u);
        Jedis jedis=new Jedis("127.0.0.1",6379);
        LOGGER.info("登陆redis");
        jedis.set(u.getpho(),String.valueOf(i));
    }
}
