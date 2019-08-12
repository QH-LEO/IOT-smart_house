package org.test;

import org.function.login;
import org.utils.userinfo;

import java.sql.SQLException;

/**
 * @Author: Hao Qin
 * @Date: 19-7-30  上午12:23
 * @Version 1.0
 */
public class test9 {
    public static void main(String[] args) throws SQLException {
        userinfo user=new userinfo();
        user.setpho("15525835333");
        login l=new login();

        System.out.println(l.Login(user).getTemprs());
    }
}
