package org.test;

import org.apache.log4j.Logger;
import org.utils.userinfo;

/**
 * @Author: Hao Qin
 * @Date: 19-7-27  下午2:09
 * @Version 1.0
 */
public class logger {
    static org.apache.log4j.Logger LOGGER= Logger.getLogger(logger.class);
    public static void main(String[] args) {
        userinfo u=new userinfo();

        System.out.println(u.gettime());
        LOGGER.warn("获取当前时间");
//搜迪斯
    }
}