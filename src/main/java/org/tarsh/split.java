package org.tarsh;

/**
 * @Author: Hao Qin
 * @Date: 19-8-13  上午8:35
 * @Version 1.0
 */
public class split {
    public static void main(String[] args) {
        String a="ws://0.0.0.0:6789/verify/55";
        System.out.println(a.split("/")[3]);
        System.out.println(a.split("/")[4]);
        System.out.println(a.split("/")[5]);
    }
}
