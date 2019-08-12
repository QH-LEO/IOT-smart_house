package org.server;

import com.google.gson.Gson;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import org.utils.userinfo;

import java.io.UnsupportedEncodingException;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @Author: Hao Qin
 * @Date: 19-7-26  上午1:44
 * @Version 1.0
 */
public class nettythread implements Runnable{
    private String result="";
    private org.utils.message message=null;
    private ChannelHandlerContext ctx=null;
    public ChannelHandlerContext getCtx() {
        return ctx;
    }
    public void setCtx(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public org.utils.message getMessage() {
        return message;
    }
    public void setMessage(org.utils.message message) {
        this.message = message;
    }

    @Override
    public void run() {
        try {
           // State state= ServletTest.doServlet(this.message);

            userinfo user=null;
            String res=new Gson().toJson(user);
            System.out.println("res-----------"+res);


            FullHttpResponse response = null;
            response = new DefaultFullHttpResponse(HTTP_1_1,
                    OK, Unpooled.wrappedBuffer(res.getBytes("UTF-8")));
            response.headers().set(CONTENT_TYPE, "application/json");
            response.headers().setInt(CONTENT_LENGTH,
                    response.content().readableBytes());

            //kua yu 请求的URL：/PictureCheck/picturecheck
            //读取的数据 :{"userid":123456,"url":"Picture/PM56czScDh.jpg"}
            response.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN,"*");


          //  if (HttpHeaderUtil.isKeepAlive(this.message.getFhr())) {
                response.headers().set(CONNECTION, KEEP_ALIVE);
           // }
//            if(state.getServer().equals("doRegister")){
//                response.headers().set(COOKIE,state.getCookies());
//            }
            this.ctx.write(response);
            this.ctx.flush();


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

