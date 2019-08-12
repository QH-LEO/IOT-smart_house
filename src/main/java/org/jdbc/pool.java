package org.jdbc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author: Hao Qin
 * @Date: 19-7-26  上午12:51
 * @Version 1.0
 */

    public class pool {
   //     ArrayBlockingQueue消息队列,中间件，抗压，！！
    //想了半天还是用blockingq吧。我真。。

        private static ArrayBlockingQueue abqcon = new ArrayBlockingQueue(200);
        static{
            try {
                String driver = "com.mysql.jdbc.Driver";
                String DB_URL = "jdbc:mysql://localhost:3306/smart";
                String USER="leo";
                String PASS="123456";
                int jdbcPoolInitSize =120;
                Class.forName(driver);
                for (int i = 0; i < jdbcPoolInitSize; i++) {
                    Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                    System.out.println("获取到了链接" + conn+"-------"+i);
                    abqcon.put(conn);
                }
            } catch (SQLException e) {
                System.out.println(" 创建数据库连接失败！ " + e.getMessage());

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public synchronized static Object get(){
            final Connection conn;
            Object con= null;
            try {
                conn= (Connection) abqcon.take();
                System.out.println("Connections数据库连接池大小是" + abqcon.size());
                return Proxy.newProxyInstance(pool.class.getClassLoader(), conn.getClass().getInterfaces(), new InvocationHandler(){
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable {
                        if(!method.getName().equals("close")){
                            return method.invoke(conn,args);
                        }else{
                            abqcon.put(conn);
                            System.out.println(conn + "被还给Connections数据库连接池了！！");
                            System.out.println("数据库连接池大小为" + abqcon.size());
                            return null;
                        }
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;


        }

}
