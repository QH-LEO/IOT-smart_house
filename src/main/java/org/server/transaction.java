package org.server;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.function.infochange;
import org.function.login;
import org.utils.message;
import org.utils.userinfo;
import org.valve.Judge_Action;
import org.valve.Judge_Login;
import org.verify.acceptsms;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: Hao Qin
 * @Date: 19-8-15  下午3:40
 * @Version 1.0
 */
public class transaction {

    public static  userinfo doServlet(message message) {
        Logger LOGGER=Logger.getLogger(org.utils.message.class);
        String url = message.getUrl();
        LOGGER.info("this is message get url"+url);
        String gson = message.getData();
      //  String response = "{\"state\":0}";
        userinfo u=new userinfo();
        String[] result = url.split("/");
        Class cls = null;
        Gson g = new Gson();
        Constructor con = null;
        try {

            LOGGER.info("请求的资源： Control." + result[1] + "===" + result[2]);
            //result[1]=============类名
            //result[2]==============方法名

            //1登陆=========================================================================================================
            switch (result[1]) {
                case "login1":
                    cls=Class.forName("org.function."+result[1]);
                    con=cls.getConstructor();
                    login login1= (login)con.newInstance();
                    Method dologin=cls.getMethod(result[2], login.class);
                    u= (userinfo) dologin.invoke(login1,new Gson().fromJson(gson,userinfo.class));
//                    state.setState(state1);
                    return u;
                case "Judge_Login":
                    cls=Class.forName("org.valve."+result[1]);
                    con=cls.getConstructor();
                    Judge_Login l= (Judge_Login)con.newInstance();
                    Method doj=cls.getMethod(result[2], Judge_Login.class);
                    u= (userinfo) doj.invoke(l,new Gson().fromJson(gson,userinfo.class));
                    return u;
                case "acceptsms":
                    cls=Class.forName("org.verify."+result[1]);
                    con=cls.getConstructor();
                    acceptsms a= (acceptsms)con.newInstance();
                    Method doa=cls.getMethod(result[2], login.class);
                    u= (userinfo) doa.invoke(a,new Gson().fromJson(gson,userinfo.class));
                    return u;
                case "infochange":
                    cls=Class.forName("org.function."+result[1]);
                    con=cls.getConstructor();
                    infochange i= (infochange)con.newInstance();
                    Method doi=cls.getMethod(result[2], login.class);
                    u= (userinfo) doi.invoke(i,new Gson().fromJson(gson,userinfo.class));
                    return u;
                case "Judge_Action":
                    cls=Class.forName("org.valve."+result[1]);
                    con=cls.getConstructor();
                    Judge_Action j=(Judge_Action) con.newInstance();
                    Method doj1=cls.getMethod(result[2], login.class);
                    u= (userinfo) doj1.invoke(j,new Gson().fromJson(gson,userinfo.class));
                    return u;

//                case "":
//                    return u;
//                case "":
//                    return u;
            }
        } catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return u;
    }

}