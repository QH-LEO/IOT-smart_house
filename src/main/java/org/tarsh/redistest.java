package org.tarsh;

import org.utils.userinfo;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.Set;

/**
 * @Author: Hao Qin
 * @Date: 19-7-30  上午12:16
 * @Version 1.0
 */
public class redistest {
    public static void main(String[] args) {
        String unimber="";
        Jedis j=new Jedis("127.0.0.1",6379);
        userinfo user=new userinfo();
        user.setpho("15525835777");
        Set ss=j.keys(user.getpho());
        Iterator iterator=ss.iterator();
        while(iterator.hasNext()){
            unimber= (String) iterator.next();
           // String p=j.get(unimber);
          //  String bb=p.replaceAll("\\{|}","");
           // System.out.println(bb);

          //  String [] result=bb.split("=");
          //  System.out.println("密码是==="+result[0]);
          //  System.out.println("用户id是"+result[1]);
           // upass=result[0];
           // uid=Integer.parseInt(result[1]);
            System.out.println("redis获取到手机号："+unimber);
        }

    }
}
