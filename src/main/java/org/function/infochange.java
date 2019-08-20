package org.function;

import org.AIvoice.WebTTs;
import org.jdbc.pool;
import org.utils.userinfo;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author: Hao Qin
 * @Date: 19-7-30  上午3:15
 * @Version 1.0
 */
public class infochange {
    public userinfo insert(userinfo u) throws SQLException, UnsupportedEncodingException {
        Connection conn= (Connection) pool.get();
        WebTTs w=new WebTTs(u);
        String S =w.getvoice();
        String sql = "INSERT INTO info (pho,p,addr,uname,voicename) VALUES (" + "'" + u.getpho() + "'" + "," + "'" + u.getp() + "'" +"," + "'" + u.getadd() + "'" +","+ "'" + u.getuname() + "'"+","+ "'" + S + "'" +")";
        Statement statement = conn.createStatement();

        statement.execute(sql);
        conn.setAutoCommit(false);
        conn.commit();
        u.setstate(1);
        return u;

    }

}