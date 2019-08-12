package org.utils;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @Author: Hao Qin
 * @Date: 19-7-26  下午1:57
 * @Version 1.0
 */

public class message {

    FullHttpRequest fhr=null;
    private String url="";
    private String data="";
    private String cookies="";

    public FullHttpRequest getFhr() {
        return fhr;
    }

    public void setFhr(FullHttpRequest fhr) {
        this.fhr = fhr;
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public message(String url, String data,String cookies) {
        this.url = url;
        this.data = data;
        this.cookies = cookies;
    }
    public message(){}
}





