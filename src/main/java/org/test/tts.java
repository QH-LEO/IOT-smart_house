package org.test;

import org.AIvoice.WebTTs;
import org.utils.userinfo;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

/**
 * @Author: Hao Qin
 * @Date: 19-8-4  下午5:12
 * @Version 1.0
 */
public class tts {
    public static void main(String[] args) throws UnsupportedEncodingException, SQLException {
        userinfo u=new userinfo();
        u.setpho("15525835777");
        u.setadd("18,6,01");
        WebTTs w=new WebTTs(u);
        String s=w.getvoice();
        System.out.println(s);

    }
}
