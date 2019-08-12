package org.jdbc;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author: Hao Qin
 * @Date: 19-7-25  下午11:20
 * @Version 1.0
 */
public class connection {
    static Logger LOGGER = Logger.getLogger(connection.class);
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/smart?";
    static final String USER = "leo";
    static final String PASS = "123456";
    static Connection conn=null;
//设计模式值得学习！！
    public static Connection getconn() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
        }
        try {
             conn= DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException e) {

//            LOGGER.debug(" in get connection"+e);
        }return conn;}}

//想怎么打log就怎么打log，略略略！！

