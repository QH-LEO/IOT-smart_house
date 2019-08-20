package org.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Hao Qin
 * @Date: 2019/7/25  13:57
 * @Version 1.0
 */
public class userinfo {
//{‘’ uname‘’:"张三"“add[]”:{23,5,01}“upho”:"15525835777""pho":"15525835777""time":"4""userinfo":t"dnum":4}
    private String uname;
    private String  add;
    private String pho;
    private String p;
    private String time;
    private int  state;
    private int  dnum;
    private String voicename;
   // public  BufferedImage image;
    private String osspath;
    public  int fileid;
    public int temprs;
    public int tempdata;


    public userinfo() {
    }

    public void setfileid(int fileid) {this.fileid = fileid;}
    public void setuname(String uname) {
        this.uname = uname;
    }

    public void setadd(String add) {
        this.add = add;
    }

    public void setp(String p) {
        this.p = p;
    }

    public void setpho(String pho) {
        this.pho = pho;
    }

    public void setdnum(int dnum) {
        this.dnum = dnum;
    }

    public void setstate(int state) {
        this.state = state;
    }

   public void settime(String time) {
        this.time =time;
    }
    public void setTempdata(int i){this.tempdata=i;}
   // public void setimage(BufferedImage image){this.image=image;}
    public void setosspath(String A){this.osspath = A;}
    public void setTemprs(int z){this.temprs=z;}
    public void setVoicename(String s){this.voicename=s;}

    public String getadd() {
        return add;
    }

    public String getuname() {
        return uname;
    }

    public String getpho() {
        return pho;
    }

    public int getstate() {
        return state;
    }

    public String getp() {
        return p;
    }

    public String gettime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :HH:mm:ss");
        String A = dateFormat.format(date);
        return A;
    }
    public int getDnum() {
        return dnum;
    }
  //  public BufferedImage getImage(){return image;}
    public int getfileid(){return fileid;}
    public String getosspath(){return osspath;}
    public int getTemprs(){return temprs;}
    public int getTempdata(){return tempdata;}
    public String getVoicename(){return voicename ;}

}
