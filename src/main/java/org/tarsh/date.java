package org.tarsh;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Hao Qin
 * @Date: 19-7-26  上午12:18
 * @Version 1.0
 */
public class date {
    public static void main(String[] args) {
//        long a=System.currentTimeMillis();
//        System.out.println(a);
        Date date = new Date();
//.........................hh  和  HH还不一样我也是醉了
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :HH:mm:ss");
        String A=dateFormat.format(date);
        System.out.println();


    }
}
