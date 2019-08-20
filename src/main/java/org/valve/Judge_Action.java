package org.valve;

import org.AIidentify.similarity;
import org.OSS.uploadobject;
import org.freeswitch.esl.client.makecall;
import org.function.findvoice;
import org.utils.userinfo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * @Author: Hao Qin
 * @Date: 19-7-26  下午1:54
 * @Version 1.0
 */
public class Judge_Action {
    //硬件摄像头照片保存路径
    private String path="/home/qhwd/smartlife/resource\\/img/01.jpg";
    private String path1="/home/qhwd/smartlife/resource\\/img/02.jpg";
    //private int i=0;

    public  String NAME;
//    public static List<String> nlist=new ArrayList<>();
    public static HashMap<Integer,String> map = new HashMap<>();
   // HashMap<String, String> map = new HashMap<>();
    //public userinfo u = new userinfo();//靠，我真是神来之笔，学到了！！！！
   public similarity s = new similarity(path,path1);
    uploadobject p=new uploadobject();
    makecall c=new makecall();
    public userinfo judge(userinfo u) throws IOException, SQLException {
        //  String gson = String.valueOf(message.class.getConstructor().newInstance());
//        Class cls = Class.forName("org.AIidentify.similarity");
//        Constructor con = cls.getConstructor();
//        similarity s = (similarity) con.newInstance(path, path1);
        findvoice f=new findvoice();


        if (s.caculate() <0.75) {
            u.setstate(1);
            // u.setpho("");//前端传入的手机号
            u.settime(u.gettime());
            int a=0;
           // u.setimage(s.image1);
            u.setosspath(p.upload(path1));
//            WebTTs w=new WebTTs(u);
//            w.getvoice();
//            NAME=w.getvoice();
//            map.put(fid++,NAME);//牛逼牛逼牛逼！！！！！！！！！！！
//            u.setfileid(fid);//牛逼牛逼牛逼！！！！！！！！！！！
   //         c.call(map.get(u.getfileid()-1));
            c.call(f.getvoicename(u));

        }
        else if(s.caculate()>=0.75&&s.caculate()<0.9){
            if (s.caculate()==0.75) {
                u.setstate(1);
                //  u.setpho("");//前端传入的手机号
                u.settime(u.gettime());
               // u.setimage(s.image1);
                u.setosspath(p.upload(path1));
                c.call(f.getvoicename(u));
                }
            }

        else if(s.caculate()>=0.9){
            u.setstate(0);
            //  u.setpho("");//前端传入的手机号
            u.settime(u.gettime());
           // u.setimage(s.image1);

        }

            return u;


    }
//        Method m = cls.getMethod("caculate", similarity.class);
//        Object n=m.invoke(s);
//        userinfo u = (userinfo) m.invoke(s, new Gson().fromJson(gson, similarity.class));
//        return u;

    public Judge_Action() throws IOException {
    }
}
