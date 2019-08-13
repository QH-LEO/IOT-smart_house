package org.valve;

import org.apache.log4j.Logger;
import org.function.login;
import org.utils.userinfo;
import redis.clients.jedis.Jedis;

import java.sql.SQLException;

/**
 * @Author: Hao Qin
 * @Date: 19-7-30  上午2:39
 * @Version 1.0
 */
public class Judge_Login {
    static Logger LOGGER=Logger.getLogger(Judge_Login.class);
    public void judgelogin(userinfo u)throws SQLException {
//        CountDownLatch begin = new CountDownLatch(2);
//        SendSms s=new SendSms();
//        int i=s.send(u);
//        begin.countDown();
        Jedis jedis=new Jedis("127.0.0.1",6379);
        LOGGER.info("登陆redis");
        String temp=jedis.get(u.getpho());
        if(temp==String.valueOf(u.getTempdata())){
            jedis.del(u.getpho());
            login a=new login();
            a.Login(u);
            //xxx
        }
    }
}
