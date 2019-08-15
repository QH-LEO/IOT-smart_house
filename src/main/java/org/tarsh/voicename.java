package org.tarsh;

import org.function.infochange;
import org.utils.userinfo;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

/**
 * @Author: Hao Qin
 * @Date: 19-8-4  下午4:46
 * @Version 1.0
 */
public class voicename {
    public static void main(String[] args) throws UnsupportedEncodingException, SQLException {
        userinfo u=new userinfo();
        u.setpho("15525835777");
        u.setadd("18,6,01");
        u.setp("1001");
        u.setuname("小李");

     //   System.out.println(((u.getadd()).split(","))[2]);

       infochange i=new infochange();
        i.insert(u);
    }
}
