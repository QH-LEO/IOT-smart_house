package org.server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

/**
 * @Author: Hao Qin
 * @Date: 19-8-15  下午3:38
 * @Version 1.0
 */
public interface OutHandler1 extends ChannelHandler {
    // 向client发送消息
    void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception;
}
