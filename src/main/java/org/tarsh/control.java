package org.tarsh;

import org.utils.userinfo;
import org.valve.Judge_Action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;



/**
 * @Author: Hao Qin
 * @Date: 19-7-27  下午11:05
 * @Version 1.0
 */
public class control {
    //牛逼牛逼，总控分路实现，开始喜欢我自己了！！！！！！
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        //System.out.println(fid);
        Judge_Action j=new Judge_Action();
        userinfo u=new userinfo();
        u.setpho("15525835777");
        j.judge(u);
       // System.out.println(fid);
        System.out.println(j.s.caculate());
        System.out.println(u.getstate());
        //System.out.println(u.getImage());
        //System.out.println(j.u.gettime());
        //System.out.println(j.u.getadd());
        //System.out.println(u.getfileid());
       // System.out.println();
        System.out.println(u.getosspath());
//        Judge_Action j1=new Judge_Action();
//        j1.judge();
//        System.out.println(fid);
//        System.out.println(j1.s.caculate());
//        System.out.println(j1.u.getstate());
//        //System.out.println(u.getImage());
//        System.out.println(j1.u.gettime());
//        //    System.out.println(j.u.getadd());
//        System.out.println(j1.u.getfileid());
//        System.out.println(j1.map.get(j1.u.getfileid()-1));


    }
}
