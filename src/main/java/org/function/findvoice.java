package org.function;

import org.jdbc.pool;
import org.utils.userinfo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author: Hao Qin
 * @Date: 19-7-26  上午3:24
 * @Version 1.0
 */
public class findvoice{

    //    private static String a=null;
//    private static String b=null;
//    private static String c=null;
    public findvoice(){}
   // String[] target;
    String a;
    public String getvoicename(userinfo u) throws SQLException {
//search
        Connection conn= (Connection) pool.get();
        String sql = "select voicename from info where pho="+ "'"+u.getpho()+"'";
        System.out.println(sql);
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()) {
             a = rs.getString(1);
           //  target = a.split(",");
        }

        return a;
    }

//    public static void main(String[] args) throws SQLException {
//        userinfo u=new userinfo();
//        u.setpho("15525835777");

//        System.out.println(f.getadd(u)[0]);
//    }


}
