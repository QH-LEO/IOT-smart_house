//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.AIvoice;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.utils.userinfo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class WebTTs {
    private static final String WEBTTS_URL = "http://api.xfyun.cn/v1/service/v1/tts";
    private static final String APPID = "5b67c3b7";
    private static final String API_KEY = "这能告诉你？";
  //  private static final String TEXT = "提醒 "+s+"号"+s1+"层"+s2+"户，发生漏水，请及时维修 提醒 "+s+"号"+s1+"层"+s2+"户，发生漏水，请及时维修";
    private static final String AUE = "raw";
    private static final String AUF = "audio/L16;rate=8000";
    private static final String SPEED = "10";
    private static final String VOLUME = "50";
    private static final String PITCH = "50";
    private static final String VOICE_NAME = "xiaoyan";
    private static final String ENGINE_TYPE = "intp65";
    private static final String TEXT_TYPE = "text";
    static String s=null;
    static String s1=null;
    static String s2=null;
    String pho="";
    //String filename="";
    public WebTTs(userinfo u) {

        s=((u.getadd()).split(","))[0];
        s1=((u.getadd()).split(","))[1];
        s2=((u.getadd()).split(","))[2];
    }

//    public WebTTs(){}

    //用户填写信息之后根据前段返回来的地址生成录音。发生异常时直接调用
    public String getvoice() throws UnsupportedEncodingException {
    //    WebTTs w=new WebTTs(pho);
        String filename;
    //    System.out.println(s);
        Map<String, String> header = buildHttpHeader();
        Map<String, Object> resultMap = HttpUtil.doPost2("http://api.xfyun.cn/v1/service/v1/tts", header, "text=" + URLEncoder.encode("提醒 "+s+"号"+s1+"层"+s2+"户，发生漏水，请及时维修,提醒 "+s+"号"+s1+"层"+s2+"户，发生漏水，请及时维修", "utf-8"));
    //    System.out.println("占用内存大小： " + URLEncoder.encode("提醒 "+s+"号"+s1+"层"+s2+"户，发生漏水，请及时维修 提醒 "+s+"号"+s1+"层"+s2+"户，发生漏水，请及时维修", "utf-8").getBytes().length);
        if ("audio/mpeg".equals(resultMap.get("Content-Type"))) {
            if ("raw".equals("raw")) {
                FileUtil.save("/usr/local/freeswitch/sounds/en/us/callie", resultMap.get("sid") + ".wav", (byte[])resultMap.get("body"));
    //            System.out.println("合成 WebAPI 调用成功，音频保存位置：resource\\" + resultMap.get("sid") + ".wav");
            } else {
                FileUtil.save("/usr/local/freeswitch/sounds/en/us/callie", resultMap.get("sid") + ".mp3", (byte[])resultMap.get("body"));
    //            System.out.println("合成 WebAPI 调用成功，音频保存位置：resource\\" + resultMap.get("sid") + ".mp3");
            }
        } else {
    //        System.out.println("合成 WebAPI 调用失败，错误信息：" + resultMap.get("body").toString());
        }
       // JSONObject json = JSONObject.fromObject(postdata);
        filename=String.valueOf(resultMap.get("sid"));
        return filename;
    }

    private static Map<String, String> buildHttpHeader() throws UnsupportedEncodingException {
        String curTime = String.valueOf(System.currentTimeMillis() / 1000L);
        String param = "{\"auf\":\"audio/L16;rate=8000\",\"aue\":\"raw\",\"voice_name\":\"xiaoyan\",\"speed\":\"10\",\"volume\":\"50\",\"pitch\":\"50\",\"engine_type\":\"intp65\",\"text_type\":\"text\"}";
        String paramBase64 = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
        String checkSum = DigestUtils.md5Hex("7537552fcb290713f1c2f20c008220d1" + curTime + paramBase64);
        Map<String, String> header = new HashMap();
        header.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        header.put("X-Param", paramBase64);
        header.put("X-CurTime", curTime);
        header.put("X-CheckSum", checkSum);
        header.put("X-Appid", "5b67c3b7");
        return header;
    }
}
