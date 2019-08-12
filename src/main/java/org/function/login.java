package org.function;

/**
 * @Author: Hao Qin
 * @Date: 19-7-25  下午10:36
 * @Version 1.0
 */

import org.apache.log4j.Logger;
import org.jdbc.pool;
import org.utils.userinfo;
import redis.clients.jedis.Jedis;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Set;

public class login{
    public login(){}

    static Logger LOGGER=Logger.getLogger(login.class);

    public static userinfo Login(userinfo user) throws SQLException {
        System.out.println("前端传入的手机号是："+user.getpho());
        String unimber="";
        Jedis jedis=new Jedis("127.0.0.1",6379);
        LOGGER.info("登陆redis");
        Set ss=jedis.keys(user.getpho());
        Iterator iterator=ss.iterator();
        while(iterator.hasNext()){
            unimber= (String) iterator.next();
        }
        if(unimber.equals(user.getpho())){
            System.out.println("从redis内匹配到用户");
            LOGGER.info("redis Login");
            user.setTemprs(1);
        }else {
            System.out.println("JEDIS没有匹配到");
            jedis.close();
            System.out.println("开始查询sql");
            LOGGER.info("MYSQL login");
            Connection conn= (Connection) pool.get();
            String sql = "select pho from info where pho=" + user.getpho();
           // System.out.println("sql=" + sql);
              LOGGER.debug("this is in search mysql" + sql);
            try {
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(sql);
//                System.out.println(rs.next());
//                boolean t=true;
                if(rs.next()){
                    LOGGER.info("mysql 成功匹配");
                    user.setTemprs(1);
                    jedis.set(user.getpho(),String.valueOf(1) );
                    jedis.expire(user.getpho(), 30 * 24 * 60);
                    //设置失效时间
                    System.out.println("redis 更新了一条数据  key=" + user.getpho());
                    jedis.close();
                }
                else{
                    user.setTemprs(0);
                    LOGGER.info("查无此人");
                }
            } catch (SQLException e) {
                LOGGER.debug("...老哥干啥玩意呢？？？", e);
            }
        }
        return user;
    }
}
