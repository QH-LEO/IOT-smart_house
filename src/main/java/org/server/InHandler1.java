package org.server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Author: Hao Qin
 * @Date: 19-8-15  下午3:35
 * @Version 1.0
 */
public interface InHandler1 extends ChannelHandler {
    void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception;
}
