package org.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import org.apache.log4j.Logger;
import org.utils.message;

/**
 * @Author: Hao Qin
 * @Date: 19-8-15  下午2:09
 * @Version 1.0
 */
public class InHandler extends ChannelHandlerAdapter implements InHandler1 {
    private static Logger log = Logger.getLogger(InHandler.class);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        // log.info("InHandler :" + ctx);
        //  log.error("请求： "+ctx);

        ChannelHandlerContext ctx1=ctx;
        Object msg1=msg;


        message message=new message();



        System.out.println("read");


        try {
//            System.out.println(msg.toString());
            FullHttpRequest fhr = (FullHttpRequest) msg;
//            System.out.println(fhr.headers().get("Cookies"));
//            message.setCookies((String) fhr.headers().get("Cookies"));
//            System.out.println(cookie.toString());
//            ByteBuf result = (ByteBuf) msg;
//            byte[] result1 = new byte[result.readableBytes()];
//            result.readBytes(result1);
            System.out.println("请求的URL：" + fhr.uri());
            message.setUrl(fhr.uri());
            message.setFhr(fhr);
            ByteBuf buf = fhr.content();
            HttpHeaders head = fhr.headers();
//            System.out.println( head.toString());
            byte[] result1 = new byte[buf.readableBytes()];
            buf.readBytes(result1);
            String data = new String(result1, "utf8");
            System.out.println("读取的数据 :"+data);
            //  log.error("读取的数据:  "+data);
            message.setData(data);
//            if (fhr.uri().equals("/Shencha/DoTask/") || fhr.uri().equals("/Shencha/DoTask/Picture")) {
//
//                CheckHandler checkHandler = new CheckHandler();
//                checkHandler.channelRead(ctx1, msg1);
//            ctx.fireChannelRead(msg);
//            } else {
//                System.out.println("第一层没有澜街道====");
            ctx.flush();
            ctx.write(message);
            System.out.println("write");
            // }
//            int a=5/0;
        }catch (Exception e){
            log.error("",e);
            e.printStackTrace();
        }
        //log.debug("--------------------------------------");
    }
}
