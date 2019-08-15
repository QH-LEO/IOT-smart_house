package org.tarsh;

import org.utils.userinfo;
import org.verify.acceptsms;

/**
 * @Author: Hao Qin
 * @Date: 19-8-4  下午12:30
 * @Version 1.0
 */
public class ttt {
    public static void main(String[] args) {

        acceptsms s=new acceptsms();
        userinfo u=new userinfo();
        u.setpho("15525835777");
        s.send1(u);
    }
}
