package org.test;

/**
 * @Author: Hao Qin
 * @Date: 19-7-28  上午3:48
 * @Version 1.0
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbclogin {

    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;
        String url = "jdbc:mysql://localhost:3306/smart?useUnicode=true&characterEncoding=utf-8";
        String user = "leo";
        String password = "123456";
        String sql;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("成功加载MySQL驱动程序");
            //建立连接
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("连接成功");

            //创建Statement
            stmt = conn.createStatement();
            sql = "drop table  if exists student";
            stmt.executeUpdate(sql);
            sql = "create table student(NO char(20),name varchar(20),primary key(NO))";
            int result = stmt.executeUpdate(sql);

            if(result == 0) {
                System.out.println("创建数据表成功");
                sql = "insert into student(NO,name) values('2012001','陶伟基')";
                result = stmt.executeUpdate(sql);
                sql = "insert into student(NO,name) values('2012002','周小俊')";
                result = stmt.executeUpdate(sql);
                sql = "select * from student where NO like \"%2012%\" ";
                rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
                System.out.println("学号\t姓名");
                while (rs.next()) {
                    System.out.println(rs.getString(1) + "\t" + rs.getString(2));// 入如果返回的是int类型可以用getInt()
                }

                //PreparedStatement的例子
                sql = "select * from student where NO like ? ";
                PreparedStatement preStatement = conn.prepareStatement(sql);
                preStatement.setString(1,"%2012%" );
                rs = preStatement.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getString(1) + "\t ** " + rs.getString(2));// 入如果返回的是int类型可以用getInt()
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("找不到驱动程序类 ，加载驱动失败！");
            e.printStackTrace();
        }finally{
            if(rs != null) {// 关闭记录集
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if(stmt != null) {// 关闭声明

                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null) {// 关闭连接对象
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}