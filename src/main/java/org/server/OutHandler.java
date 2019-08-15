package org.server;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import org.apache.log4j.Logger;
import org.utils.message;

/**
 * @Author: Hao Qin
 * @Date: 19-8-15  下午2:10
 * @Version 1.0
 */
public class OutHandler extends ChannelHandlerAdapter implements OutHandler1 {
    private static Logger log = Logger.getLogger(OutHandler.class);
    @Override
    // 向client发送消息
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
//        Message message= (Message) msg;
//        State state=ServletTest.doServlet(message);
//        String res=new Gson().toJson(state);
//        System.out.println("res-----------");
//        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
//                OK, Unpooled.wrappedBuffer(res.getBytes("UTF-8")));
//        response.headers().set(CONTENT_TYPE, "application/json");

//        response.headers().setInt(CONTENT_LENGTH,
//                response.content().readableBytes());
//        if(state.getServer().equals("doRegister")){
//            response.headers().set(COOKIE,state.getCookies());
//        }
//        ctx.write(response);
//        ctx.flush();
        System.out.println(ctx.channel().isOpen());
        System.out.println(ctx.channel().isActive());
        System.out.println(ctx.channel().isWritable());
        System.out.println(ctx.channel().isRegistered());
        WorkRunable run=new WorkRunable();
        run.setCtx(ctx);
        run.setMessage((message) msg);
        WorkThreadPool.doWork(run);
    }
}
